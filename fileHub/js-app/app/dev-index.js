import fetchMock from '../node_modules/fetch-mock/esm/client.js';

const registeredUsers = new Map()
    .set('qwe@qwe.qwe', '123456')
    .set('artem.vvv@hand', '654321')
    .set('vasya@kakk', '123654')
    .set('email.jj@jash.com', '654123');

fetchMock.post('/login', (url, opts) => {
  const body = opts.body;
  const email = JSON.parse(body).email;
  const password = JSON.parse(body).password;

  if (registeredUsers.has(email)) {
    if (registeredUsers.get(email) === password) {
      return {token: 'AUTH_TOKEN'};
    } else {
      return {
        status: 400,
        body: {message: 'Incorrect password'},
      };
    }
  } else {
    return {
      status: 403,
      body: {message: 'No registered user'},
    };
  }
});

fetchMock.post('/register', (url, opts) => {
  const body = JSON.parse(opts.body);
  const email = body.email;
  const password = body.password;

  if (registeredUsers.has(email)) {
    return {
      status: 400,
      body: {message: `User with email: «${email}» already existed`},
    };
  } else {
    return {
      email: email,
      password: password,
    };
  }
});

fetchMock.get('/folder/:5', () => {
  return {
    id: '5',
    name: 'temp_file',
    type: 'folder',
    itemsAmount: 45,
    parentFolderId: 'vadsdvs',
  };
});
/* fetchMock.post('/register', () => {
  return {
    status: 422,
    body: [
      {field: 'email', message: 'this is message'},
      {field: 'password', message: 'this is password message'},
    ],
  };
});*/

/*
fetchMock.post('/register', () => {
  return {
    status: 500,
    body: {
      message: 'This is server error',
    },
  };
});
*/
