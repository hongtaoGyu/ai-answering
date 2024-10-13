import { createApp } from "vue";
import App from "./App.vue";
import ArcoVue from "@arco-design/web-vue";
import { createPinia } from "pinia";
import "@arco-design/web-vue/dist/arco.css";
import router from "./router";
import "@/access";

const pinia = createPinia();
const app = createApp(App);

app.use(ArcoVue).use(pinia).use(router).mount("#app");
