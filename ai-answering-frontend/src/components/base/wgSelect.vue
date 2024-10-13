<template>
  <a-select
    v-model="selectedValue"
    :options="selectOptions"
    @change="handleChange"
  >
    <template #option="{ option }" v-if="showLabel">
      <span>{{ option.label }}</span>
    </template>
  </a-select>
</template>

<script lang="ts" setup>
import { defineProps, onMounted, ref, onBeforeMount, defineEmits } from "vue";
import API from "@/api";
import $wg from "@/whiteGoose";
import { selectOptionGetSelectOptionDataByName } from "@/api/selectOptionController";

const props = defineProps(["options", "selectOptionName", "showLabel"]);
const selectOptions = ref(props.options);
const showLabel = ref(true);
const emit = defineEmits<{
  (e: "update:modelValue", value: any): void;
}>();

const handleChange = (value: string | number) => {
  emit("update:modelValue", value);
};

onMounted(() => {
  if (!props.options && $wg.isNotWhiteSpace(props.selectOptionName)) {
    debugger;
    selectOptionGetSelectOptionDataByName({
      name: props.selectOptionName.toString(),
    }).then((res) => {
      console.log("res.data", res.data);
      selectOptions.value = res.data.data;
    });
  }
});
onBeforeMount(() => {
  if (props.showLabel !== undefined && props.showLabel !== null) {
    showLabel.value = props.showLabel;
  }
});
</script>
