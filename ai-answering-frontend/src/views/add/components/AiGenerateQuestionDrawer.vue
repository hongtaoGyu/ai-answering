<template>
  <a-button type="outline" @click="handleClick">AI 生成题目</a-button>
  <a-drawer
    :visible="visible"
    :width="340"
    unmountOnClose
    @cancel="handleCancel"
    @ok="handleOk"
  >
    <template #title>AI 生成题目</template>
    <div>
      <a-form
        :model="form"
        auto-label-width
        label-align="left"
        @submit="handleSubmit"
      >
        <a-form-item label="应用 id">
          {{ appId }}
        </a-form-item>
        <a-form-item field="questionNumber" label="题目数量">
          <a-input-number
            v-model="form.questionNumber"
            max="20"
            min="0"
            placeholder="请输入题目数量"
          />
        </a-form-item>
        <a-form-item field="optionNumber" label="选项数量">
          <a-input-number
            v-model="form.optionNumber"
            max="6"
            min="0"
            placeholder="请输入选项数量"
          />
        </a-form-item>
        <a-form-item>
          <a-button
            :loading="submitting"
            html-type="submit"
            style="width: 120px"
            type="primary"
          >
            {{ submitting ? "生成中" : "一键生成" }}
          </a-button>
          <a-button
            :loading="submitting"
            style="width: 120px"
            @click="handleSSE"
          >
            {{ submitting ? "生成中" : "实时生成" }}
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </a-drawer>
</template>

<script lang="ts" setup>
import { defineProps, reactive, ref, withDefaults } from "vue";
import API from "@/api";
import { questionAiGenerate } from "@/api/questionController";
import message from "@arco-design/web-vue/es/message";

interface Props {
  appId: string;
  onSuccess?: (result: API.QuestionContentDTO[]) => void;
  onSSESuccess?: (result: API.QuestionContentDTO) => void;
  onSSEStart?: (event: any) => void;
  onSSEClose?: (event: any) => void;
}

const props = withDefaults(defineProps<Props>(), {
  appId: () => {
    return "";
  },
});

const form = reactive({
  optionNumber: 2,
  questionNumber: 10,
} as API.AiGenerateQuestionRequest);

const visible = ref(false);
const submitting = ref(false);

const handleClick = () => {
  visible.value = true;
};
const handleOk = () => {
  visible.value = false;
};
const handleCancel = () => {
  visible.value = false;
};

/**
 * 提交
 */
const handleSubmit = async () => {
  if (!props.appId) {
    return;
  }
  submitting.value = true;
  const res = await questionAiGenerate({
    appId: props.appId as any,
    ...form,
  });
  if (res.data.code === 0 && res.data.data.length > 0) {
    if (props.onSuccess) {
      props.onSuccess(res.data.data);
    } else {
      message.success("生成题目成功");
    }
    // 关闭抽屉
    handleCancel();
  } else {
    message.error("操作失败，" + res.data.message);
  }
  submitting.value = false;
};

const handleSSE = async () => {
  if (!props.appId) {
    return;
  }
  submitting.value = true;

  const eventSource = new EventSource(
    `/api/question/ai_generate/sse?appId=${props.appId}&optionNumber=${
      form.optionNumber
    }&questionNumber=${form.questionNumber}&_t=${Date.now()}`
  );
  eventSource.onmessage = function (ev) {
    console.log(ev.data);
    props.onSSESuccess?.(JSON.parse(ev.data));
  };
  eventSource.onerror = function (ev) {
    if (ev.eventPhase === EventSource.CLOSED) {
      console.log("正常关闭连接");
      eventSource.close();
      submitting.value = false;
      props.onSSEClose?.(ev);
    }
  };
  eventSource.onopen = function (ev) {
    console.log("建立连接");
    props.onSSEStart?.(ev);
    handleCancel();
  };
};
</script>
