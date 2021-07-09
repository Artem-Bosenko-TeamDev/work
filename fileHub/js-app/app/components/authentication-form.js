import {Component} from './component.js';
import {Form} from './form.js';
import {FormGroupBox} from './form-group-box.js';
import {Validator} from '../validation/validator.js';
import {ParameterConfiguration, ValidationConfiguration} from '../validation/validationConfiguration.js';
import {lengthValidation, structureValidation} from '../validation/validation-rules.js';

/**
 * This is.
 */
export class AuthenticationForm extends Component {
  initNestedComponents() {
    this._form = new Form(this.rootElement);
    this._form.header = 'Sign In to FileHub';
    this._form.buttonTitle = 'Sign In';
    this._form.linkMessage = 'Didn\'t have an account yet?';
    this._form.linkReference = 'registration.html';

    this._form.initInputs((container) => {
      this._emailBox = new FormGroupBox(container);
      this._passwordBox = new FormGroupBox(container);

      this._emailBox.id = 'email-user';
      this._emailBox.title = 'Email';
      this._emailBox.inputType = 'text';

      this._passwordBox.id = 'password-user';
      this._passwordBox.title = 'Password';
      this._passwordBox.inputType = 'text';
    });

    this._form.onSubmit = async () => {
      this._emailBox.cleanErrorMessage();
      this._passwordBox.cleanErrorMessage();

      const authenticationFormValidator = new Validator(
          new ValidationConfiguration(
              [
                new ParameterConfiguration(
                    lengthValidation(this._emailBox, this._emailBox.inputValue, 5)),
                new ParameterConfiguration(
                    structureValidation(this._emailBox, this._emailBox.inputValue)),
                new ParameterConfiguration(
                    lengthValidation(this._passwordBox, this._passwordBox.inputValue, 6)),
              ],
          ),
      );

      this._form.validateForm(authenticationFormValidator);
    };
  }

  get markup() {
    return `<div class="raw"></div>`;
  }
}
