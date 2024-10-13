<template>
  <wg-table v-bind="tableProps" @resetHandle="resetHandle" ref="table">
    <template #resultPicture="{ dataRecord }">
      <a-image :src="dataRecord.resultPicture" width="64" />
    </template>
    <template #scoringStrategy="{ dataRecord }">
      {{ APP_SCORING_STRATEGY_MAP[dataRecord.scoringStrategy] }}
    </template>
    <template #appType="{ dataRecord }">
      {{ APP_TYPE_MAP[dataRecord.appType] }}
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
import { ref } from "vue";
import API from "@/api";
import WgTable from "@/components/base/wgTable.vue";
import { APP_SCORING_STRATEGY_MAP, APP_TYPE_MAP } from "../../constant/app";
import dayjs from "dayjs";

const formSearchParams = ref<API.UserAnswerVO>({});

// 初始化搜索条件（不应该被修改）
const initSearchParams = {
  pageIndex: 1,
  pageSize: 10,
};

const searchParams = ref<API.GetDataListRequest>({
  ...initSearchParams,
});
const dataList = ref<API.UserAnswer[]>([]);
const total = ref<number>(0);

// 表格列配置
const columns = [
  {
    title: "id",
    dataIndex: "id",
  },
  {
    title: "选项",
    dataIndex: "choices",
  },
  {
    title: "结果 id",
    dataIndex: "resultId",
    filter: true,
    searchCondition: {
      name: "resultId",
      value: null,
      type: 1,
      dataType: "string",
      dataSource: [],
    },
  },
  {
    title: "名称",
    dataIndex: "resultName",
  },
  {
    title: "描述",
    dataIndex: "resultDesc",
  },
  {
    title: "图片",
    dataIndex: "resultPicture",
    slotName: "resultPicture",
  },
  {
    title: "得分",
    dataIndex: "resultScore",
    filter: true,
    searchCondition: {
      name: "result_score",
      type: 1,
      dataType: "number",
      dataSource: [],
    },
  },
  {
    title: "应用 id",
    dataIndex: "appId",
  },
  {
    title: "应用类型",
    dataIndex: "appType",
    slotName: "appType",
    filter: true,
    searchCondition: {
      name: "app_type",
      type: 1,
      dataType: "select",
      dataSource: "select_app_type",
    },
  },
  {
    title: "评分策略",
    dataIndex: "scoringStrategy",
    slotName: "scoringStrategy",
  },
  {
    title: "用户 id",
    dataIndex: "createUserId",
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
  apiController: "userAnswer",
  actions: [
    { code: "delete", name: "删除" },
    { code: "reset", name: "重置" },
  ],
});

const resetHandle = function (row: any) {
  console.log("调到了", row);
};
</script>
