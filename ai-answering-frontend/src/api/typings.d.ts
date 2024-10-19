declare namespace API {
  type AiGenerateQuestionRequest = {
    appId?: number;
    optionNumber?: number;
    questionNumber?: number;
  };

  type App = {
    appDesc?: string;
    appIcon?: string;
    appId?: number;
    appName?: string;
    appType?: number;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    id?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    scoringStrategy?: number;
    stateCode?: string;
    tableName?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type appActivatedDataParams = {
    /** id */
    id: number;
  };

  type appDeactivatedDataParams = {
    /** id */
    id: number;
  };

  type appDeleteDataParams = {
    /** id */
    id: number;
  };

  type appGetAppUserVOParams = {
    /** id */
    id: number;
  };

  type appGetDataParams = {
    /** id */
    id: number;
  };

  type AppQueryRequest = {
    appDesc?: string;
    appIcon?: string;
    appName?: string;
    appType?: number;
    id?: number;
    notId?: number;
    pageIndex?: number;
    pageSize?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewerId?: number;
    scoringStrategy?: number;
    searchText?: string;
    sortField?: string;
    sortOrder?: string;
    userId?: number;
  };

  type AppReviewVO = {
    appId?: number;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type AppUserVO = {
    appDesc?: string;
    appIcon?: string;
    appId?: number;
    appName?: string;
    appType?: number;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    scoringStrategy?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
    user?: UserVO;
  };

  type AppVO = {
    appDesc?: string;
    appIcon?: string;
    appId?: number;
    appName?: string;
    appType?: number;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    scoringStrategy?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type BaseResponseAppUserVO_ = {
    code?: number;
    data?: AppUserVO;
    message?: string;
  };

  type BaseResponseAppVO_ = {
    code?: number;
    data?: AppVO;
    message?: string;
  };

  type BaseResponseBoolean_ = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseListQuestionContentDTO_ = {
    code?: number;
    data?: QuestionContentDTO[];
    message?: string;
  };

  type BaseResponseListSelectOptionDetail_ = {
    code?: number;
    data?: SelectOptionDetail[];
    message?: string;
  };

  type BaseResponseLoginUserVO_ = {
    code?: number;
    data?: LoginUserVO;
    message?: string;
  };

  type BaseResponseLong_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageApp_ = {
    code?: number;
    data?: PageApp_;
    message?: string;
  };

  type BaseResponsePageAppUserVO_ = {
    code?: number;
    data?: PageAppUserVO_;
    message?: string;
  };

  type BaseResponsePageQuestion_ = {
    code?: number;
    data?: PageQuestion_;
    message?: string;
  };

  type BaseResponsePageScoringResult_ = {
    code?: number;
    data?: PageScoringResult_;
    message?: string;
  };

  type BaseResponsePageSelectOption_ = {
    code?: number;
    data?: PageSelectOption_;
    message?: string;
  };

  type BaseResponsePageSelectOptionDetail_ = {
    code?: number;
    data?: PageSelectOptionDetail_;
    message?: string;
  };

  type BaseResponsePageUser_ = {
    code?: number;
    data?: PageUser_;
    message?: string;
  };

  type BaseResponsePageUserAnswer_ = {
    code?: number;
    data?: PageUserAnswer_;
    message?: string;
  };

  type BaseResponsePageUserVO_ = {
    code?: number;
    data?: PageUserVO_;
    message?: string;
  };

  type BaseResponseQuestionVO_ = {
    code?: number;
    data?: QuestionVO;
    message?: string;
  };

  type BaseResponseScoringResultVO_ = {
    code?: number;
    data?: ScoringResultVO;
    message?: string;
  };

  type BaseResponseSelectOptionDetailVO_ = {
    code?: number;
    data?: SelectOptionDetailVO;
    message?: string;
  };

  type BaseResponseSelectOptionVO_ = {
    code?: number;
    data?: SelectOptionVO;
    message?: string;
  };

  type BaseResponseString_ = {
    code?: number;
    data?: string;
    message?: string;
  };

  type BaseResponseUser_ = {
    code?: number;
    data?: User;
    message?: string;
  };

  type BaseResponseUserAnswerVO_ = {
    code?: number;
    data?: UserAnswerVO;
    message?: string;
  };

  type BaseResponseUserAnswerWithUserVO_ = {
    code?: number;
    data?: UserAnswerWithUserVO;
    message?: string;
  };

  type BaseResponseUserVO_ = {
    code?: number;
    data?: UserVO;
    message?: string;
  };

  type DeleteRequest = {
    userId?: number;
  };

  type fileUploadParams = {
    biz?: string;
  };

  type GetDataListRequest = {
    pageIndex?: number;
    pageSize?: number;
    queryText?: string;
    searchCondition?: SearchCondition[];
  };

  type LoginUserVO = {
    userAvatar?: string;
    userId?: number;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type Option = {
    key?: string;
    result?: string;
    score?: number;
    value?: string;
  };

  type OrderItem = {
    asc?: boolean;
    column?: string;
  };

  type PageApp_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: App[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageAppUserVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: AppUserVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageQuestion_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: Question[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageScoringResult_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: ScoringResult[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageSelectOption_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: SelectOption[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageSelectOptionDetail_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: SelectOptionDetail[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUser_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: User[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserAnswer_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserAnswer[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type Question = {
    appId?: number;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    id?: number;
    questionContent?: string;
    questionId?: number;
    stateCode?: string;
    tableName?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type questionActivatedDataParams = {
    /** id */
    id: number;
  };

  type QuestionContentDTO = {
    options?: Option[];
    title?: string;
  };

  type questionDeactivatedDataParams = {
    /** id */
    id: number;
  };

  type questionDeleteDataParams = {
    /** id */
    id: number;
  };

  type questionGetDataParams = {
    /** id */
    id: number;
  };

  type QuestionVO = {
    appId?: number;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    questionContent?: string;
    questionContentDTOList?: QuestionContentDTO[];
    questionId?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type ScoringResult = {
    appId?: number;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    id?: number;
    resultDesc?: string;
    resultName?: string;
    resultPicture?: string;
    resultProp?: string;
    resultPropList?: string[];
    resultScoreRange?: number;
    scoringResultId?: number;
    stateCode?: string;
    tableName?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type scoringResultActivatedDataParams = {
    /** id */
    id: number;
  };

  type scoringResultDeactivatedDataParams = {
    /** id */
    id: number;
  };

  type scoringResultDeleteDataParams = {
    /** id */
    id: number;
  };

  type scoringResultGetDataParams = {
    /** id */
    id: number;
  };

  type ScoringResultVO = {
    appId?: number;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    resultDesc?: string;
    resultName?: string;
    resultPicture?: string;
    resultProp?: string;
    resultPropList?: string[];
    resultScoreRange?: number;
    scoringResultId?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type SearchCondition = {
    name?: string;
    type?: string;
    value?: string;
  };

  type SelectOption = {
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    id?: number;
    name?: string;
    remark?: string;
    remarkName?: string;
    selectOptionId?: number;
    stateCode?: string;
    tableName?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type selectOptionActivatedDataParams = {
    /** id */
    id: number;
  };

  type selectOptionDeactivatedDataParams = {
    /** id */
    id: number;
  };

  type selectOptionDeleteDataParams = {
    /** id */
    id: number;
  };

  type SelectOptionDetail = {
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    id?: number;
    label?: string;
    selectOptionDetailId?: number;
    selectOptionId?: number;
    stateCode?: string;
    tableName?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
    value?: string;
  };

  type selectOptionDetailActivatedDataParams = {
    /** id */
    id: number;
  };

  type selectOptionDetailDeactivatedDataParams = {
    /** id */
    id: number;
  };

  type selectOptionDetailDeleteDataParams = {
    /** id */
    id: number;
  };

  type selectOptionDetailGetDataParams = {
    /** id */
    id: number;
  };

  type SelectOptionDetailVO = {
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    label?: string;
    selectOptionDetailId?: number;
    selectOptionId?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
    value?: string;
  };

  type selectOptionGetDataParams = {
    /** id */
    id: number;
  };

  type selectOptionGetSelectOptionDataByNameParams = {
    /** name */
    name: string;
  };

  type SelectOptionVO = {
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    name?: string;
    remark?: string;
    remarkName?: string;
    selectOptionId?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
  };

  type User = {
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    mpOpenId?: string;
    stateCode?: string;
    unionId?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
    userAccount?: string;
    userAvatar?: string;
    userId?: number;
    userName?: string;
    userPassword?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserAddRequest = {
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userRole?: string;
  };

  type UserAnswer = {
    appId?: number;
    appType?: number;
    choices?: string;
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    id?: number;
    resultDesc?: string;
    resultId?: number;
    resultName?: string;
    resultPicture?: string;
    resultScore?: number;
    scoringStrategy?: number;
    stateCode?: string;
    tableName?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
    userAnswerId?: number;
  };

  type userAnswerActivatedDataParams = {
    /** id */
    id: number;
  };

  type userAnswerDeactivatedDataParams = {
    /** id */
    id: number;
  };

  type userAnswerDeleteDataParams = {
    /** id */
    id: number;
  };

  type userAnswerGetDataParams = {
    /** id */
    id: number;
  };

  type userAnswerGetUserAnswerWithUserParams = {
    /** id */
    id: number;
  };

  type UserAnswerVO = {
    appId?: number;
    appType?: number;
    choices?: string;
    choicesList?: string[];
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    resultDesc?: string;
    resultId?: number;
    resultName?: string;
    resultPicture?: string;
    resultScore?: number;
    scoringStrategy?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
    userAnswerId?: number;
  };

  type UserAnswerWithUserVO = {
    appId?: number;
    appType?: number;
    choices?: string;
    choicesList?: string[];
    createTime?: string;
    createUserId?: number;
    createUserName?: string;
    resultDesc?: string;
    resultId?: number;
    resultName?: string;
    resultPicture?: string;
    resultScore?: number;
    scoringStrategy?: number;
    stateCode?: string;
    updateTime?: string;
    updateUserId?: number;
    updateUserName?: string;
    user?: UserVO;
    userAnswerId?: number;
  };

  type userGetParams = {
    /** id */
    id?: number;
  };

  type userGetVoParams = {
    /** id */
    id?: number;
  };

  type UserLoginRequest = {
    userAccount?: string;
    userPassword?: string;
  };

  type userLoginWxOpenParams = {
    /** code */
    code: string;
  };

  type UserQueryRequest = {
    mpOpenId?: string;
    pageIndex?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    unionId?: string;
    userId?: number;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserRegisterRequest = {
    checkPassword?: string;
    userAccount?: string;
    userPassword?: string;
  };

  type UserUpdateMyRequest = {
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
  };

  type UserUpdateRequest = {
    userAvatar?: string;
    userId?: number;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserVO = {
    userAvatar?: string;
    userId?: number;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };
}
