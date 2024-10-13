// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** activatedData GET /api/scoringResult/activatedData */
export async function scoringResultActivatedData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.scoringResultActivatedDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseScoringResultVO_>(
    "/api/scoringResult/activatedData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** createData POST /api/scoringResult/createData */
export async function scoringResultCreateData(
  body: API.ScoringResultVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseScoringResultVO_>(
    "/api/scoringResult/createData",
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

/** deactivatedData GET /api/scoringResult/deactivatedData */
export async function scoringResultDeactivatedData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.scoringResultDeactivatedDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseScoringResultVO_>(
    "/api/scoringResult/deactivatedData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** deleteData GET /api/scoringResult/deleteData */
export async function scoringResultDeleteData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.scoringResultDeleteDataParams,
  options?: {
    [key: string]: any;
  }
) {
  return request<API.BaseResponseScoringResultVO_>(
    "/api/scoringResult/deleteData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** getData GET /api/scoringResult/getData */
export async function scoringResultGetData( // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.scoringResultGetDataParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseScoringResultVO_>(
    "/api/scoringResult/getData",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** getDataList POST /api/scoringResult/getDataList */
export async function scoringResultGetDataList(
  body: API.GetDataListRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageScoringResult_>(
    "/api/scoringResult/getDataList",
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

/** saveOrUpdateData POST /api/scoringResult/saveOrUpdateData */
export async function scoringResultSaveOrUpdateData(
  body: API.ScoringResultVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseScoringResultVO_>(
    "/api/scoringResult/saveOrUpdateData",
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

/** updateData POST /api/scoringResult/updateData */
export async function scoringResultUpdateData(
  body: API.ScoringResultVO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseScoringResultVO_>(
    "/api/scoringResult/updateData",
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
