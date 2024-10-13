<template>
  <wg-table ref="table" v-bind="tableProps">
    <template #resultPicture="{ dataRecord }">
      <a-image :src="dataRecord.resultPicture" width="64" />
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
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import WgTable from "@/components/base/wgTable.vue";

// 表格列配置
const columns = [
  {
    title: "id",
    dataIndex: "id",
  },
  {
    title: "名称",
    dataIndex: "resultName",
    filter: true,
    searchCondition: {
      name: "result_name",
      type: 0,
      dataType: "string",
    },
  },
  {
    title: "描述",
    dataIndex: "resultDesc",
    filter: true,
    searchCondition: {
      name: "result_desc",
      type: 0,
      dataType: "string",
    },
  },
  {
    title: "图片",
    dataIndex: "resultPicture",
    slotName: "resultPicture",
  },
  {
    title: "结果属性",
    dataIndex: "resultProp",
  },
  {
    title: "评分范围",
    dataIndex: "resultScoreRange",
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
      type: 0,
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
  apiController: "scoringResult",
  actions: [{ code: "delete", name: "删除" }],
});
</script>
