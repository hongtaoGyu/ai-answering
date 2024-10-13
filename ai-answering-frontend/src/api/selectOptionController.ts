// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** activatedData GET /api/selectOption/activatedData */
export async function selectOptionActivatedData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionActivatedDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseSelectOptionVO_>(
    "/api/selectOption/activatedData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** createData POST /api/selectOption/createData */
export async function selectOptionCreateData(
  body: API.SelectOptionVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseSelectOptionVO_>(
    "/api/selectOption/createData",
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

/** deactivatedData GET /api/selectOption/deactivatedData */
export async function selectOptionDeactivatedData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionDeactivatedDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseSelectOptionVO_>(
    "/api/selectOption/deactivatedData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** deleteData GET /api/selectOption/deleteData */
export async function selectOptionDeleteData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionDeleteDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseSelectOptionVO_>(
    "/api/selectOption/deleteData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** getData GET /api/selectOption/getData */
export async function selectOptionGetData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionGetDataParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseSelectOptionVO_>("/api/selectOption/getData", {
    method: "GET",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getDataList POST /api/selectOption/getDataList */
export async function selectOptionGetDataList(
  body: API.GetDataListRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageSelectOption_>(
    "/api/selectOption/getDataList",
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

/** getSelectOptionDataByName GET /api/selectOption/getSelectOptionDataByName */
export async function selectOptionGetSelectOptionDataByName( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.selectOptionGetSelectOptionDataByNameParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseListSelectOptionDetail_>(
    "/api/selectOption/getSelectOptionDataByName",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** saveOrUpdateData POST /api/selectOption/saveOrUpdateData */
export async function selectOptionSaveOrUpdateData(
  body: API.SelectOptionVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseSelectOptionVO_>(
    "/api/selectOption/saveOrUpdateData",
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

/** updateData POST /api/selectOption/updateData */
export async function selectOptionUpdateData(
  body: API.SelectOptionVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseSelectOptionVO_>(
    "/api/selectOption/updateData",
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
