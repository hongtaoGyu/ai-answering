const { generateService } = require("@umijs/openapi");

generateService({
  requestLibPath: "import request from '@/request'",
  schemaPath: "http://localhost:8101/api/v2/api-docs",
  serversPath: "./src",
  hook: {
    customFunctionName: (data) => convertToCamelCase(data.path),
  },
});

function convertToCamelCase(path) {
  // 移除 '/api/' 子串
  const cleanedPath = path.replace("/api/", "");

  // 按照 '/' 分割字符串
  const parts = cleanedPath.split("/");

  // 转换为小驼峰形式
  const camelCaseParts = parts.map((part, index) => {
    if (index === 0) {
      return part; // 第一个单词首字母小写
    }
    return part.charAt(0).toUpperCase() + part.slice(1); // 其他单词首字母大写
  });

  // 连接成最终字符串
  return camelCaseParts.join("");
}
