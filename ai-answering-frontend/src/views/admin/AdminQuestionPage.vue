<template>
  <wg-table v-bind="tableProps">
    <template #questionContent="{ dataRecord }">
      <div
        v-for="question in JSON.parse(dataRecord.questionContent)"
        :key="question.title"
      >
        {{ question }}
      </div>
    </template>
    <template #createTime="{ dataRecord }">
      {{ dayjs(dataRecord.createTime).format("YYYY-MM-DD HH:mm:ss") }}
    </template>
    <template #updateTime="{ dataRecord }">
      {{ dayjs(dataRecord.updateTime).format("YYYY-MM-DD HH:mm:ss") }}
    </template>
  </wg-table>
</template>

<script lang="ts" setup>
import { ref, watchEffect } from "vue";
import {
  questionDeleteData,
  questionGetDataList,
} from "@/api/questionController";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import WgTable from "@/components/base/wgTable.vue";

// 初始化搜索条件（不应该被修改）
const initSearchParams = {
  pageIndex: 1,
  pageSize: 10,
};

const searchParams = ref<API.GetDataListRequest>({
  ...initSearchParams,
});
const dataList = ref<API.Question[]>([]);
const total = ref<number>(0);

/**
 * 加载数据
 */
const loadData = async () => {
  const res = await questionGetDataList(searchParams.value);
  if (res.data.code === 0) {
    dataList.value = res.data.data?.records || [];
    total.value = res.data.data?.total || 0;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};

/**
 * 删除
 * @param record
 */
const doDelete = async (record: API.Question) => {
  if (!record.id) {
    return;
  }

  const res = await questionDeleteData({
    id: record.id,
  });
  if (res.data.code === 0) {
    loadData();
  } else {
    message.error("删除失败，" + res.data.message);
  }
};

/**
 * 监听 searchParams 变量，改变时触发数据的重新加载
 */
watchEffect(() => {
  loadData();
});

// 表格列配置
const columns = [
  {
    title: "id",
    dataIndex: "id",
  },
  {
    title: "题目内容",
    dataIndex: "questionContent",
    slotName: "questionContent",
  },
  {
    title: "应用 id",
    dataIndex: "appId",
    filter: true,
    searchCondition: {
      name: "appId",
      type: 1,
      dataType: "string",
    },
  },
  {
    title: "用户 id",
    dataIndex: "createUserId",
    filter: true,
    searchCondition: {
      name: "createUserId",
      type: 1,
      dataType: "string",
    },
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    slotName: "createTime",
  },
  {
    title: "更新时间",
    dataIndex: "updateTime",
    slotName: "updateTime",
  },
];

const tableProps = ref({
  columns,
  originExtraParams: {},
  apiController: "question",
  actions: [{ code: "delete", name: "删除" }],
});
</script>
