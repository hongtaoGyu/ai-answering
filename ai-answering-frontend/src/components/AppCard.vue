<template>
  <a-card class="appCard" hoverable @click="doCardClick">
    <template #actions>
      <!--      <span class="icon-hover"> <IconThumbUp /> </span>-->
      <span class="icon-hover"> <IconShareInternal /> </span>
    </template>
    <template #cover>
      <div
        :style="{
          height: '204px',
          overflow: 'hidden',
        }"
      >
        <img
          :alt="app.appName"
          :src="app.appIcon"
          :style="{ width: '100%', transform: 'translateY(-20px)' }"
        />
      </div>
    </template>
    <a-card-meta :description="app.appDesc" :title="app.appName">
      <template #avatar>
        <div
          :style="{ display: 'flex', alignItems: 'center', color: '#1D2129' }"
        >
          <a-avatar
            :image-url="app.user?.userAvatar"
            :size="24"
            :style="{ marginRight: '8px' }"
          />
          <a-typography-text
            >{{ app.user?.userName ?? "无名" }}
          </a-typography-text>
        </div>
      </template>
    </a-card-meta>
  </a-card>
</template>

<script lang="ts" setup>
import { IconShareInternal } from "@arco-design/web-vue/es/icon";
import API from "@/api";
import { defineProps, withDefaults } from "vue";
import { useRouter } from "vue-router";

interface Props {
  app: API.AppUserVO;
}

const props = withDefaults(defineProps<Props>(), {
  app: () => {
    return {};
  },
});

const router = useRouter();
const doCardClick = () => {
  router.push(`/app/detail/${props.app.id}`);
};
</script>
<style scoped>
.appCard {
  cursor: pointer;
}

.icon-hover {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  transition: all 0.1s;
}

.icon-hover:hover {
  background-color: rgb(var(--gray-2));
}
</style>
