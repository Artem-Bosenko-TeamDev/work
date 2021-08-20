import fetchMock from '../../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../../app/services/api-service/api-service.js';
import {getWindowMock} from '../../../mock-window-api-service.js';

const {module, test} = QUnit;

export default () => module('uploadFile', () => {
  test('Should handle a response with code 200', async (assert) => {
    const id = 'folder';
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/folder/5/file',
      method: 'POST',
    }, {id});
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    const result = await apiService.uploadFile({file: 'file'}, '5');

    assert.ok(fetch.called(), 'Should send a request');
    assert.equal(result.id, id, 'Should get id of uploaded file');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/folder/5/file',
      method: 'POST',
    }, 400);
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    try {
      await apiService.uploadFile({file: 'file'}, '5');
    } catch (error) {
      assert.equal(error.message, '400: client error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });

  test('Should handle a response with code 500', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/folder/5/file',
      method: 'POST',
    }, 500);
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    try {
      await apiService.uploadFile({file: 'file'}, '5');
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
