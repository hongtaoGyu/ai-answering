<template>
  <!--  <a-form-->
  <!--    :model="formSearchParams"-->
  <!--    :style="{ marginBottom: '20px' }"-->
  <!--    layout="inline"-->
  <!--    @submit="doSearch"-->
  <!--  >-->
  <!--    <a-form-item field="resultName" label="结果名称">-->
  <!--      <a-input-->
  <!--        v-model="formSearchParams.resultName"-->
  <!--        allow-clear-->
  <!--        placeholder="请输入结果名称"-->
  <!--      />-->
  <!--    </a-form-item>-->
  <!--    <a-form-item field="resultDesc" label="结果描述">-->
  <!--      <a-input-->
  <!--        v-model="formSearchParams.resultDesc"-->
  <!--        allow-clear-->
  <!--        placeholder="请输入结果描述"-->
  <!--      />-->
  <!--    </a-form-item>-->
  <!--    <a-form-item field="appId" label="应用 id">-->
  <!--      <a-input-->
  <!--        v-model="formSearchParams.appId"-->
  <!--        allow-clear-->
  <!--        placeholder="请输入应用 id"-->
  <!--      />-->
  <!--    </a-form-item>-->
  <!--    <a-form-item field="userId" label="用户 id">-->
  <!--      <a-input-->
  <!--        v-model="formSearchParams.createUserId"-->
  <!--        allow-clear-->
  <!--        placeholder="请输入用户 id"-->
  <!--      />-->
  <!--    </a-form-item>-->
  <!--    <a-form-item>-->
  <!--      <a-button html-type="submit" style="width: 100px" type="primary">-->
  <!--        搜索-->
  <!--      </a-button>-->
  <!--    </a-form-item>-->
  <!--  </a-form>-->
  <!--  <a-table-->
  <!--    :columns="columns"-->
  <!--    :data="dataList"-->
  <!--    :pagination="{-->
  <!--      showTotal: true,-->
  <!--      pageSize: searchParams.pageSize,-->
  <!--      current: searchParams.current,-->
  <!--      total,-->
  <!--    }"-->
  <!--    @page-change="onPageChange"-->
  <!--  >-->
  <!--    <template #resultPicture="{ record }">-->
  <!--      <a-image :src="record.resultPicture" width="64" />-->
  <!--    </template>-->
  <!--    <template #appType="{ record }">-->
  <!--      {{ APP_TYPE_MAP[record.appType] }}-->
  <!--    </template>-->
  <!--    <template #scoringStrategy="{ record }">-->
  <!--      {{ APP_SCORING_STRATEGY_MAP[record.scoringStrategy] }}-->
  <!--    </template>-->
  <!--    <template #createTime="{ record }">-->
  <!--      {{ dayjs(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}-->
  <!--    </template>-->
  <!--    <template #updateTime="{ record }">-->
  <!--      {{ dayjs(record.updateTime).format("YYYY-MM-DD HH:mm:ss") }}-->
  <!--    </template>-->
  <!--    <template #optional="{ record }">-->
  <!--      <a-space>-->
  <!--        <a-button status="danger" @click="doDelete(record)">删除</a-button>-->
  <!--      </a-space>-->
  <!--    </template>-->
  <!--  </a-table>-->

  <wg-table v-bind="tableProps">
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
    <template #optional="{ dataRecord }">
      <a-space>
        <a-button status="danger" @click="doDelete(dataRecord)">删除</a-button>
      </a-space>
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

/**
 * 加载数据
 */
// const loadData = async () => {
//   const res = await userAnswerGetDataList(searchParams.value);
//   if (res.data.code === 0) {
//     dataList.value = res.data.data?.records || [];
//     total.value = res.data.data?.total || 0;
//   } else {
//     message.error("获取数据失败，" + res.data.message);
//   }
// };

// /**
//  * 执行搜索
//  */

// //         {name:"字段名", value:"数值", type: "类型"}
// const doSearch = () => {
//   let searchCondition: any = [];
//   if ($wg.isNotWhiteSpace(formSearchParams.value?.resultName)) {
//     searchCondition.push({
//       name: "resultName",
//       value: formSearchParams.value.resultName,
//       type: "0",
//     });
//   }
//   if ($wg.isNotWhiteSpace(formSearchParams.value?.resultDesc)) {
//     searchCondition.push({
//       name: "result_desc",
//       value: formSearchParams.value.resultDesc,
//       type: "0",
//     });
//   }
//   if ($wg.isNotWhiteSpace(formSearchParams.value?.appId)) {
//     searchCondition.push({
//       name: "appId",
//       value: formSearchParams.value.appId,
//       type: "1",
//     });
//   }
//   if ($wg.isNotWhiteSpace(formSearchParams.value?.createUserId)) {
//     searchCondition.push({
//       name: "createUserId",
//       value: formSearchParams.value.createUserId,
//       type: "1",
//     });
//   }
//   searchParams.value = {
//     ...initSearchParams,
//     searchCondition,
//   };
// };

// /**
//  * 当分页变化时，改变搜索条件，触发数据加载
//  * @param page
//  */
// const onPageChange = (page: number) => {
//   searchParams.value = {
//     ...searchParams.value,
//     pageIndex: page,
//   };
// };

// /**
//  * 删除
//  * @param record
//  */
// const doDelete = async (record: API.UserAnswer) => {
//   if (!record.id) {
//     return;
//   }

//   const res = await userAnswerDeleteData({
//     id: record.id,
//   });
//   if (res.data.code === 0) {
//     loadData();
//   } else {
//     message.error("删除失败，" + res.data.message);
//   }
// };

// /**
//  * 监听 searchParams 变量，改变时触发数据的重新加载
//  */
// watchEffect(() => {
//   loadData();
// });
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
  {
    title: "操作",
    slotName: "optional",
  },
];

const tableProps = ref({
  columns,
  originExtraParams: {},
  apiController: "userAnswer",
});
</script>
