<template>
  <wg-table ref="srTable" v-bind="tableProps" @modifyHandle="doUpdate">
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
import { defineExpose, defineProps, ref, withDefaults } from "vue";
import API from "@/api";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import WgTable from "@/components/base/wgTable.vue";

const srTable = ref();

interface Props {
  appId: string;
  doUpdate: (scoringResult: API.ScoringResultVO) => void;
}

const props = withDefaults(defineProps<Props>(), {
  appId: () => {
    return "";
  },
});

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
    title: "创建时间",
    dataIndex: "createTime",
    slotName: "createTime",
  },
  {
    title: "更新时间",
    dataIndex: "updateTime",
    slotName: "updateTime",
  },
  {
    title: "操作",
    slotName: "optional",
  },
];

const tableProps = ref({
  columns,
  originExtraParams: [{ type: "1", value: props.appId, name: "appId" }],
  apiController: "scoringResult",
  actions: [
    { code: "delete", name: "删除" },
    { code: "modify", name: "修改" },
  ],
});

const loadData = function () {
  return srTable.value.loadTable();
};

defineExpose({ loadData });
</script>
