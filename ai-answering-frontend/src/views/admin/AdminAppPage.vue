<template>
  <wg-table
    v-bind="tableProps"
    @passHandle="doAppReview($event, '1', '审核通过')"
    @noPassHandle="doAppReview($event, '2', '审核不通过')"
    ref="table"
  >
    <template #appIcon="{ dataRecord }">
      <a-image :src="dataRecord.appIcon" width="64" />
    </template>
    <template #appType="{ dataRecord }">
      {{ APP_TYPE_MAP[dataRecord.appType] }}
    </template>
    <template #scoringStrategy="{ dataRecord }">
      {{ APP_SCORING_STRATEGY_MAP[dataRecord.scoringStrategy] }}
    </template>
    <template #reviewStatus="{ dataRecord }">
      {{ REVIEW_STATUS_MAP[dataRecord.reviewStatus] }}
    </template>
    <template #reviewTime="{ dataRecord }">
      {{
        dataRecord.reviewTime &&
        dayjs(dataRecord.reviewTime).format("YYYY-MM-DD HH:mm:ss")
      }}
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
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import {
  APP_SCORING_STRATEGY_MAP,
  APP_TYPE_MAP,
  REVIEW_STATUS_MAP,
} from "@/constant/app";
import WgTable from "@/components/base/wgTable.vue";
import { appReview } from "@/api/appController";

/**
 * 审核
 * @param record
 * @param reviewStatus
 * @param reviewMessage
 */
const doAppReview = function (record, reviewStatus, reviewMessage) {
  doReview(record, reviewStatus, reviewMessage);
};

const table = ref();

const doReview = async (
  record: any,
  reviewStatus: number,
  reviewMessage?: string
) => {
  if (!record.id) {
    return;
  }

  const res = await appReview({
    appId: record.id,
    reviewStatus,
    reviewMessage,
  });
  if (res.data.code === 0) {
    table.value.loadTable();
  } else {
    message.error("审核失败，" + res.data.message);
  }
};

// 表格列配置
const columns = [
  {
    title: "id",
    dataIndex: "id",
  },
  {
    title: "名称",
    dataIndex: "appName",
    filter: true,
    searchCondition: {
      name: "app_name",
      type: 0,
      dataType: "string",
    },
  },
  {
    title: "描述",
    dataIndex: "appDesc",
    filter: true,
    searchCondition: {
      name: "app_desc",
      type: 0,
      dataType: "string",
    },
  },
  {
    title: "图标",
    dataIndex: "appIcon",
    slotName: "appIcon",
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
    title: "审核状态",
    dataIndex: "reviewStatus",
    slotName: "reviewStatus",
  },
  {
    title: "审核信息",
    dataIndex: "reviewMessage",
  },
  {
    title: "审核时间",
    dataIndex: "reviewTime",
    slotName: "reviewTime",
  },
  {
    title: "审核人 id",
    dataIndex: "reviewerId",
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
  apiController: "app",
  actions: [
    { code: "pass", name: "通过" },
    { code: "noPass", name: "拒绝" },
    { code: "delete", name: "删除" },
  ],
});
</script>
