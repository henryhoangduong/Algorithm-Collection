import { ERROR_MSG_TOO_FEW_ARGUMENTS } from "./errors.js";

export function isObject(maybeObject) {
  return typeof maybeObject === "object" && maybeObject !== null;
}

export function isFunction(maybeFunction) {
  return typeof maybeFunction === "function";
}

export function isUndefined(maybeUndefined) {
  return (
    maybeUndefined === void 0 &&
    checkArgumentsLength(arguments, 1, "isUndefined")
  );
}

export function isIterable(maybeIterable) {
  return (
    (isObject(maybeIterable) || isFunction(maybeIterable)) &&
    typeof maybeIterable[Symbol.iterator] === "function"
  );
}

let checkArgumentsLength = (args, expectedArgsLength, fname) => {
  if (args.length >= expectedArgsLength) {
    return true;
  } else {
    throw new TypeError(
      ERROR_MSG_TOO_FEW_ARGUMENTS(fname, expectedArgsLength, args.length),
    );
  }
};
