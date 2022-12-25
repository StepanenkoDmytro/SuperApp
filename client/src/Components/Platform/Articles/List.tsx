import { Divider, List, ListItem } from '@mui/material';
import { Link } from 'react-router-dom';

export function ArticlesList() {

	const articles = [
		{
			id: 1,
			title: 'first',
			description: 'Article about first',
			tags: ['code', 'investments', 'money'],
			stars: 15
		},
		{
			id: 2,
			title: 'second',
			description: 'Article about second',
			tags: ['code'],
			stars: 3
		},
		{
			id: 3,
			title: 'third',
			description: 'Article about third',
			tags: ['investments', 'money'],
			stars: 9
		}
	];

	return (
		<>
			<p>filter component</p>
			<section>
				<List>
					{articles.map(article => <ListItem key={article.id} style={{ flexDirection: 'column'}}>
						<Link to={article.id.toString()}>
							<h1>{article.title}</h1>
							<p>{article.description}</p>
						</Link>
						<div>{article.stars} ⭐️ | tags: {article.tags.map(tag => <span> #{ tag }</span>)}</div>
						<Divider> --- </Divider>
					</ListItem>)}
				</List>
			</section>
		</>
	);
}
