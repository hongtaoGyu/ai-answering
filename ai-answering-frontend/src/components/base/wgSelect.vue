<template>
  <a-select
    v-model="selectedValue"
    :options="selectOptions"
    @change="handleChange"
  >
    <template v-if="showLabel" #option="{ option }">
      <span>{{ option.label }}</span>
    </template>
  </a-select>
</template>

<script lang="ts" setup>
import { defineEmits, defineProps, onBeforeMount, onMounted, ref } from "vue";
import $wg from "@/whiteGoose";
import { selectOptionGetSelectOptionDataByName } from "@/api/selectOptionController";

const props = defineProps(["options", "selectOptionName", "showLabel"]);
const selectOptions = ref(props.options);
const showLabel = ref(true);
const selectedValue = ref(props.options);
const emit = defineEmits<{
  (e: "update:modelValue", value: any): void;
}>();

const handleChange = (value: string | number) => {
  emit("update:modelValue", value);
};

onBeforeMount(() => {
  if (!props.options && $wg.isNotWhiteSpace(props.selectOptionName)) {
    selectOptions.value = [];
    selectOptionGetSelectOptionDataByName({
      name: props.selectOptionName.toString(),
    }).then((res) => {
      console.log("res.data", res.data);
      selectOptions.value = res.data.data;
      console.log("1111", selectOptions.value);
    });
  }

  if (props.showLabel !== undefined && props.showLabel !== null) {
    showLabel.value = props.showLabel;
  }
});
</script>
