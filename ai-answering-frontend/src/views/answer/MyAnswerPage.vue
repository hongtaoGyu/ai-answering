<template>
  <wg-table ref="table" v-bind="tableProps" @resetHandle="resetHandle">
    <template #resultPicture="{ dataRecord }">
      <a-image :src="dataRecord.resultPicture" width="64" />
    </template>
    <template #appType="{ dataRecord }">
      {{ APP_TYPE_MAP[dataRecord.appType] }}
    </template>
    <template #scoringStrategy="{ dataRecord }">
      {{ APP_SCORING_STRATEGY_MAP[dataRecord.scoringStrategy] }}
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
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import { APP_SCORING_STRATEGY_MAP, APP_TYPE_MAP } from "@/constant/app";
import WgTable from "@/components/base/wgTable.vue";
import { useLoginUserStore } from "@/store/userStore";

const loginUserStore = useLoginUserStore();

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
  },
  {
    title: "名称",
    dataIndex: "resultName",
    filter: true,
    searchCondition: {
      name: "result_name",
      value: null,
      type: 0,
      dataType: "string",
      dataSource: [],
    },
  },
  {
    title: "描述",
    dataIndex: "resultDesc",
    filter: true,
    searchCondition: {
      name: "result_desc",
      value: null,
      type: 0,
      dataType: "string",
      dataSource: [],
    },
  },
  {
    title: "图片",
    dataIndex: "resultPicture",
    slotName: "resultPicture",
  },
  {
    title: "得分",
    dataIndex: "resultScore",
  },
  {
    title: "应用 id",
    dataIndex: "appId",
    filter: true,
    searchCondition: {
      name: "appId",
      value: null,
      type: 1,
      dataType: "string",
      dataSource: [],
    },
  },
  {
    title: "应用类型",
    dataIndex: "appType",
    slotName: "appType",
  },
  {
    title: "评分策略",
    dataIndex: "scoringStrategy",
    slotName: "scoringStrategy",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    slotName: "createTime",
  },
  {
    title: "操作",
    slotName: "optional",
  },
];

const tableProps = ref({
  columns,
  originExtraParams: [
    { type: "1", value: loginUserStore.loginUser.userId, name: "createUserId" },
  ],
  apiController: "userAnswer",
  actions: [{ code: "delete", name: "删除" }],
});
</script>
