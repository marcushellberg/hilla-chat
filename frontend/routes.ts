import { Route } from '@vaadin/router';
import './views/empty/empty-view';

export type ViewRoute = Route & {
  title?: string;
  icon?: string;
  children?: ViewRoute[];
};

export const views: ViewRoute[] = [
  // place routes below (more info https://hilla.dev/docs/routing)
  {
    path: '',
    component: 'empty-view',
    icon: 'la la-file',
    title: 'Empty',
  },
];
export const routes: ViewRoute[] = [...views];
