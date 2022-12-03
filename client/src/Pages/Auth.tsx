import React from 'react';
import styles from '../styles/Pages/auth.module.scss';
import classNames from 'classnames/bind';
import { Login } from '../Components/Auth';
import GoogleIcon from '@mui/icons-material/Google';
import FacebookIcon from '@mui/icons-material/Facebook';
import GitHubIcon from '@mui/icons-material/GitHub';
import { Button, Divider, Paper } from '@mui/material';

let cx = classNames.bind(styles);

export function Auth() {
	const h1Classes = cx({header: true});
	return (
		<section className={ styles.authPage }>
			<div className={ styles.contentBlock }>
				<Paper className={ styles.paper } elevation={3}>
					<h1 className={ styles.header }>Welcome to Super App! 👋</h1>
					<section className="social">
						Sign in with:
						<div className={ styles.social }>
							<Button variant="outlined" startIcon={<GoogleIcon />}>Google</Button>
							<Button variant="outlined" startIcon={<FacebookIcon />}>Facebook</Button>
							<Button variant="outlined" startIcon={<GitHubIcon />}>Github</Button>
						</div>
					</section>
					<Divider className="mttt-4">Or continue with</Divider>

					<Login />
				</Paper>
			</div>
		</section>
	);
}
