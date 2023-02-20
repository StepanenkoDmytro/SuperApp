import { createBrowserRouter, redirect } from 'react-router-dom';
import { Auth, Landing, Platform } from './Pages';
import React from 'react';
import { ArticlesList, ArticleView } from './Components/Platform/Articles';

export const router = createBrowserRouter([
	{
		path: '/',
		loader: () => redirect('landing')
	},
	{
		path: 'landing',
		element: <Landing />
	},
	{
		path: 'auth',
		element: <Auth />
	},
	{
		path: 'platform',
		element: <Platform />,
		children: [
			{
				path: 'articles',
				// loader: () => redirect('articles/list'),
				children: [
					{
						path: '',
						element: <ArticlesList />
					},
					{
						path: ':id',
						element: <ArticleView />
					}
				]
			}
		]
	}
]);
