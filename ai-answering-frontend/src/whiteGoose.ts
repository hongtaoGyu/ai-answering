const $wg = {
  isNotWhiteSpace: function (str: string | null | undefined | number): boolean {
    return str !== null && str !== undefined && str.toString().trim() !== "";
  },
  isNullOrWhiteSpace: function (
    str: string | null | undefined | number
  ): boolean {
    return str === null || str === undefined || str.toString().trim() === "";
  },
};

export default $wg;
