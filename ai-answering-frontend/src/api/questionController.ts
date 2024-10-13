// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** activatedData GET /api/question/activatedData */
export async function questionActivatedData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.questionActivatedDataParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionVO_>("/api/question/activatedData", {
    method: "GET",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** createData POST /api/question/createData */
export async function questionCreateData(
  body: API.QuestionVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionVO_>("/api/question/createData", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** deactivatedData GET /api/question/deactivatedData */
export async function questionDeactivatedData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.questionDeactivatedDataParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionVO_>("/api/question/deactivatedData", {
    method: "GET",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteData GET /api/question/deleteData */
export async function questionDeleteData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.questionDeleteDataParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionVO_>("/api/question/deleteData", {
    method: "GET",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getData GET /api/question/getData */
export async function questionGetData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.questionGetDataParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionVO_>("/api/question/getData", {
    method: "GET",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getDataList POST /api/question/getDataList */
export async function questionGetDataList(
  body: API.GetDataListRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageQuestion_>("/api/question/getDataList", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** saveOrUpdateData POST /api/question/saveOrUpdateData */
export async function questionSaveOrUpdateData(
  body: API.QuestionVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionVO_>(
    "/api/question/saveOrUpdateData",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: body,
      ...(options || {}),
    }
  );
}

/** updateData POST /api/question/updateData */
export async function questionUpdateData(
  body: API.QuestionVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionVO_>("/api/question/updateData", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}
