import {Component} from '../components/component.js';
import {UserDetails} from './user-details.js';
import {LogOut} from './log-out.js';

/**
 * Component that rendered at header at user main page.
 */
export class FileListHeaderPanel extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const userDetails = new UserDetails(this.rootElement);
    userDetails.userFullName = 'Artem Bosenko';
    new LogOut(this.rootElement);
  }

  /** @inheritDoc */
  get _markup() {
    return `<ul data-fh="user-panel" class="panel">
            </ul>`;
  }
}
