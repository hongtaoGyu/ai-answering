<template>
  <div id="addAppPage">
    <h2 style="margin-bottom: 32px">创建应用</h2>
    <a-form
      :model="form"
      :style="{ width: '480px' }"
      auto-label-width
      label-align="left"
      @submit="handleSubmit"
    >
      <a-form-item field="appName" label="应用名称">
        <a-input v-model="form.appName" placeholder="请输入应用名称" />
      </a-form-item>
      <a-form-item field="appDesc" label="应用描述">
        <a-input v-model="form.appDesc" placeholder="请输入应用描述" />
      </a-form-item>
      <a-form-item field="appIcon" label="应用图标">
        <a-input v-model="form.appIcon" placeholder="请输入应用图标" />
      </a-form-item>
      <!--      <a-form-item field="appIcon" label="应用图标">-->
      <!--        <PictureUploader-->
      <!--          :value="form.appIcon"-->
      <!--          :onChange="(value) => (form.appIcon = value)"-->
      <!--        />-->
      <!--      </a-form-item>-->
      <a-form-item field="appType" label="应用类型">
        <wg-select
          v-model="form.appType"
          :show-label="false"
          allow-clear
          placeholder="请选择应用类型"
          selectOptionName="select_app_type"
        ></wg-select>
      </a-form-item>
      <a-form-item field="scoringStrategy" label="评分策略">
        <wg-select
          v-model="form.scoringStrategy"
          :show-label="false"
          allow-clear
          placeholder="请选择评分策略"
          selectOptionName="select_scoring_strategy"
        ></wg-select>
      </a-form-item>
      <a-form-item>
        <a-button html-type="submit" style="width: 120px" type="primary">
          提交
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import { defineProps, ref, watchEffect, withDefaults } from "vue";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { appGetData, appSaveOrUpdateData } from "@/api/appController";
import WgSelect from "@/components/base/wgSelect.vue";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => {
    return "";
  },
});

const router = useRouter();

const form = ref({
  appDesc: "",
  appIcon: "",
  appName: "",
  appType: null,
  scoringStrategy: null,
});

const oldApp = ref<API.AppVO>();

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await appGetData({
    id: props.id as any,
  });
  if (res.data.code === 0 && res.data.data) {
    res.data.data.scoringStrategy = res.data.data.scoringStrategy.toString();
    res.data.data.appType = res.data.data.appType.toString();
    res.data.data.reviewStatus = res.data.data.reviewStatus.toString();
    oldApp.value = res.data.data;
    form.value = res.data.data;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};

// 获取旧数据
watchEffect(() => {
  loadData();
});

/**
 * 提交
 */
const handleSubmit = async () => {
  let res: any;
  // 如果是修改
  res = await appSaveOrUpdateData({
    appId: props.id as any,
    ...form.value,
  });
  if (res.data.code === 0) {
    message.success("操作成功，即将跳转到应用详情页");
    setTimeout(() => {
      console.log("props", props);
      router.push(`/app/detail/${props.id || res.data.data.appId}`);
    }, 3000);
  } else {
    message.error("操作失败，" + res.data.message);
  }
};
</script>
