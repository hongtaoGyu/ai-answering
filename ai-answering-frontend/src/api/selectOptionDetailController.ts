// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** activatedData GET /api/selectOptionDetail/activatedData */
export async function selectOptionDetailActivatedData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionDetailActivatedDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseSelectOptionDetailVO_>(
    "/api/selectOptionDetail/activatedData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** createData POST /api/selectOptionDetail/createData */
export async function selectOptionDetailCreateData(
  body: API.SelectOptionDetailVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseSelectOptionDetailVO_>(
    "/api/selectOptionDetail/createData",
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

/** deactivatedData GET /api/selectOptionDetail/deactivatedData */
export async function selectOptionDetailDeactivatedData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionDetailDeactivatedDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseSelectOptionDetailVO_>(
    "/api/selectOptionDetail/deactivatedData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** deleteData GET /api/selectOptionDetail/deleteData */
export async function selectOptionDetailDeleteData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionDetailDeleteDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseSelectOptionDetailVO_>(
    "/api/selectOptionDetail/deleteData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** getData GET /api/selectOptionDetail/getData */
export async function selectOptionDetailGetData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionDetailGetDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseSelectOptionDetailVO_>(
    "/api/selectOptionDetail/getData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** getDataList POST /api/selectOptionDetail/getDataList */
export async function selectOptionDetailGetDataList(
  body: API.GetDataListRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageSelectOptionDetail_>(
    "/api/selectOptionDetail/getDataList",
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

/** saveOrUpdateData POST /api/selectOptionDetail/saveOrUpdateData */
export async function selectOptionDetailSaveOrUpdateData(
  body: API.SelectOptionDetailVO,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseSelectOptionDetailVO_>(
    "/api/selectOptionDetail/saveOrUpdateData",
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

/** updateData POST /api/selectOptionDetail/updateData */
export async function selectOptionDetailUpdateData(
  body: API.SelectOptionDetailVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseSelectOptionDetailVO_>(
    "/api/selectOptionDetail/updateData",
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
