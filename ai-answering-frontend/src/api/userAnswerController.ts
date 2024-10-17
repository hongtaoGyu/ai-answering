// @ts-ignore
/* eslint-disable */
import request from '@/request';

/** activatedData GET /api/userAnswer/activatedData */
export async function userAnswerActivatedData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.userAnswerActivatedDataParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserAnswerVO_>('/api/userAnswer/activatedData', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** createData POST /api/userAnswer/createData */
export async function userAnswerCreateData(
  body: API.UserAnswerVO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserAnswerVO_>('/api/userAnswer/createData', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deactivatedData GET /api/userAnswer/deactivatedData */
export async function userAnswerDeactivatedData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.userAnswerDeactivatedDataParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserAnswerVO_>('/api/userAnswer/deactivatedData', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteData GET /api/userAnswer/deleteData */
export async function userAnswerDeleteData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.userAnswerDeleteDataParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserAnswerVO_>('/api/userAnswer/deleteData', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getData GET /api/userAnswer/getData */
export async function userAnswerGetData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.userAnswerGetDataParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserAnswerVO_>('/api/userAnswer/getData', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getDataList POST /api/userAnswer/getDataList */
export async function userAnswerGetDataList(
  body: API.GetDataListRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageUserAnswer_>('/api/userAnswer/getDataList', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getUserAnswerWithUser GET /api/userAnswer/getUserAnswerWithUser */
export async function userAnswerGetUserAnswerWithUser(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.userAnswerGetUserAnswerWithUserParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserAnswerWithUserVO_>('/api/userAnswer/getUserAnswerWithUser', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** saveOrUpdateData POST /api/userAnswer/saveOrUpdateData */
export async function userAnswerSaveOrUpdateData(
  body: API.UserAnswerVO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserAnswerVO_>('/api/userAnswer/saveOrUpdateData', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** updateData POST /api/userAnswer/updateData */
export async function userAnswerUpdateData(
  body: API.UserAnswerVO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserAnswerVO_>('/api/userAnswer/updateData', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
