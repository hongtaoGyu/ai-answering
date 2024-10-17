<template>
  <a-form
    ref="formRef"
    :model="searchConditionList"
    :style="{ marginBottom: '20px' }"
    layout="inline"
    @submit="doSearch"
  >
    <a-form-item
      v-for="(item, index) in searchConditionList"
      :key="index"
      :label="item.title"
      field="value"
    >
      <a-input-number
        v-if="item.dataType === 'number'"
        v-model="item.value"
        :placeholder="`请输入${item.title}`"
        allow-clear
      />
      <wg-select
        v-if="item.dataType === 'select'"
        v-model="item.value"
        :options="item.options"
        :placeholder="`请选择${item.title}`"
        :selectOptionName="item.selectOptionName"
        :show-label="false"
        allow-clear
      ></wg-select>
      <a-input
        v-if="item.dataType === 'string'"
        v-model="item.value"
        :placeholder="`请输入${item.title}`"
        allow-clear
      />
    </a-form-item>
    <!--    <a-form-item>-->
    <!--      <a-button style="width: 100px" type="outline" @click="reset">-->
    <!--        重置-->
    <!--      </a-button>-->
    <!--    </a-form-item>-->
    <a-form-item>
      <a-button html-type="submit" style="width: 100px" type="primary">
        搜索
      </a-button>
    </a-form-item>
  </a-form>
  <a-table
    :columns="tableProps.columns"
    :data="dataList"
    :pagination="{
      showTotal: true,
      pageSize: baseParams.pageSize,
      current: baseParams.pageIndex,
      total: totals,
    }"
    @page-change="onPageChange"
  >
    <template
      v-for="(field, index) in diyColumns"
      :key="index"
      #[field.slotName]="{ record }"
    >
      <slot :dataRecord="record" :name="field.slotName"></slot>
    </template>

    <template
      v-if="tableProps.actions && tableProps.actions.length > 0"
      #actions="{ record }"
    >
      <a-space>
        <a-button
          v-for="(action, index) in tableProps.actions"
          :key="index"
          status="danger"
          @click="handleTableAction(action.code, record)"
          >{{ action.name }}
        </a-button>
      </a-space>
    </template>
  </a-table>
</template>

<script lang="ts" setup>
import {
  computed,
  defineEmits,
  defineExpose,
  defineProps,
  onMounted,
  reactive,
  ref,
} from "vue";
import request from "@/request";
import API from "@/api";
import WgSelect from "@/components/base/wgSelect.vue";
import $wg from "@/whiteGoose";

// 分页参数
const baseParams = ref({ pageIndex: 1, pageSize: 10 });
// 拓展搜索参数
let queryParams = ref<any>({});
const formRef = ref(null);

//表格数据
let dataList = ref<any>([]);
let totals = ref<number>(0);
const tableProps = defineProps([
  "columns",
  "originExtraParams",
  "apiController",
  "actions",
]);
const searchConditionData = ref([]);
const testnum = ref(null);
let searchConditionList = reactive([]);

const emit = defineEmits<{
  (e: string, value: any): void;
}>();

const onPageChange = (pageIndex: number) => {
  baseParams.value.pageIndex = pageIndex;
  loadTable();
};

const loadTable = function () {
  let allParams = { ...baseParams.value };
  dataList.value = [];
  const list = queryParams.value.searchConditionList.filter(
    (item: any) => item.value || item.value === 0
  );

  if (list.length > 0) {
    allParams = Object.assign(allParams, {
      searchCondition: list.map((item: any) => ({
        name: item.name,
        value: item.value,
        type: item.type,
      })),
    });
  }
  if (tableProps.originExtraParams && tableProps.originExtraParams.length > 0) {
    if (list.length > 0) {
      console.log("111");
      allParams = Object.assign(allParams, {
        searchCondition: [...list, ...tableProps.originExtraParams],
      });
    } else {
      console.log("222");
      allParams = Object.assign(allParams, {
        searchCondition: tableProps.originExtraParams,
      });
    }
  }
  getData(allParams).then((res: any) => {
    if (res?.data?.data) {
      if (res.data.data.records) {
        res.data.data.records.forEach((item: any) => {
          dataList.value.push(item);
        });
      }
      dataList.value = res.data?.data?.records || [];
      totals.value = Number(res.data?.data?.total || 0);
    }
  });
  // .catch((err) => {
  // });
};

const getData = async function (
  body: API.GetDataListRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageUserAnswer_>(
    `/api/${tableProps.apiController}/getDataList`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: body,
      ...(options || {}),
    }
  );
};

const doSearch = () => {
  baseParams.value.pageIndex = 1;
  loadTable();
  console.log("搜索");
};

const reset = () => {
  if (queryParams.value.searchConditionList.length > 0) {
    queryParams.value.searchConditionList.forEach((item: any) => {
      item.value = null;
    });
  }
  console.log("computed", searchConditionList);
  if (searchConditionList.length > 0) {
    searchConditionList.forEach((item: any) => {
      item.value = null;
    });
  }
  if (formRef.value) {
    formRef.value.resetFields();
  }

  baseParams.value.pageIndex = 1;
  loadTable();
};

const diyColumns = computed(() => {
  return tableProps.columns.filter((item: any) => item.slotName);
});

onMounted(() => {
  if (tableProps?.actions?.length > 0) {
    // eslint-disable-next-line vue/no-mutating-props
    tableProps.columns.push({
      title: "操作",
      dataIndex: "actions",
      slotName: "actions",
    });
  }
  searchConditionList = tableProps.columns
    .filter((item: any) => item.filter)
    .map((mitem: any) => {
      let obj = {
        ...mitem.searchCondition,
        title: mitem.title,
      };
      if (Array.isArray(mitem.searchCondition.dataSource)) {
        obj.selectOptionName = null;
        obj.options = mitem.searchCondition.dataSource;
      } else if ($wg.isNotWhiteSpace(mitem.searchCondition.dataSource)) {
        obj.selectOptionName = mitem.searchCondition.dataSource;
        obj.options = null;
      } else {
        obj.selectOptionName = null;
        obj.options = null;
      }

      return obj;
    });
  queryParams.value = {
    searchConditionList,
  };
  searchConditionData.value = searchConditionList;
  loadTable();
});

const handleTableAction = function (code: string, row: any) {
  if (code === "delete") {
    console.log("删除", row);
    deleteRow({ id: row.id }).then(() => {
      loadTable();
    });
  } else if (code === "edit") {
    console.log("编辑", row);
  } else {
    emit(`${code}Handle`, row);
  }
};

const deleteRow = async function (
  params: any,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageUserAnswer_>(
    `/api/${tableProps.apiController}/deleteData`,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
};
defineExpose({ loadTable });
</script>
