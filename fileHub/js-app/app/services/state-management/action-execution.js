/**
 * The abstract base for initializing execution of special action.
 */
export default class ActionExecutor {
  /**
   * @param {ActionInfo} actionInfo  - data for running action executor.
   * @param {object} services
   * @param {object} state
   * @param {function} mutate
   */
  apply(actionInfo, services, state, mutate) {
  }
}
