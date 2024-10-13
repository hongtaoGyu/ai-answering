// @ts-ignore
/* eslint-disable */
import request from '@/request';

/** activatedData GET /api/app/activatedData */
export async function appActivatedData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.appActivatedDataParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppVO_>('/api/app/activatedData', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** createData POST /api/app/createData */
export async function appCreateData(body: API.AppVO, options?: { [key: string]: any }) {
  return request<API.BaseResponseAppVO_>('/api/app/createData', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deactivatedData GET /api/app/deactivatedData */
export async function appDeactivatedData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.appDeactivatedDataParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppVO_>('/api/app/deactivatedData', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteData GET /api/app/deleteData */
export async function appDeleteData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.appDeleteDataParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppVO_>('/api/app/deleteData', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getData GET /api/app/getData */
export async function appGetData(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.appGetDataParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppVO_>('/api/app/getData', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getDataList POST /api/app/getDataList */
export async function appGetDataList(
  body: API.GetDataListRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageApp_>('/api/app/getDataList', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** doAppReview POST /api/app/review */
export async function appReview(body: API.AppReviewVO, options?: { [key: string]: any }) {
  return request<API.BaseResponseBoolean_>('/api/app/review', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** saveOrUpdateData POST /api/app/saveOrUpdateData */
export async function appSaveOrUpdateData(body: API.AppVO, options?: { [key: string]: any }) {
  return request<API.BaseResponseAppVO_>('/api/app/saveOrUpdateData', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** updateData POST /api/app/updateData */
export async function appUpdateData(body: API.AppVO, options?: { [key: string]: any }) {
  return request<API.BaseResponseAppVO_>('/api/app/updateData', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
