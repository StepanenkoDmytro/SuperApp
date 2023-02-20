import React, { useState } from 'react';
import styles from '../styles/Pages/auth.module.scss';
import { Login, Register } from '../Components/Auth';
import GoogleIcon from '@mui/icons-material/Google';
import FacebookIcon from '@mui/icons-material/Facebook';
import GitHubIcon from '@mui/icons-material/GitHub';
import { Button, Divider, Link, Paper } from '@mui/material';

// TODO: move to App.js
import '../styles/global.scss';

export function Auth() {
	const [isAccountExist, setIsAccountExist]: [boolean, any] = useState(true);

	console.log(styles);

	return (
		<section className={ styles.authPage }>
			<div className={ styles.contentBlock }>
				<Paper className={ styles.paper } elevation={3}>
					<h1 className={ styles.header }>Welcome to Super App! 👋</h1>
					<section className="social m-b-25 m-t-25">
						<p className="m-b-10">Sign in with:</p>
						<div className={ styles.social }>
							<Button variant="outlined" startIcon={<GoogleIcon />}>Google</Button>
							<Button variant="outlined" startIcon={<FacebookIcon />}>Facebook</Button>
							<Button variant="outlined" startIcon={<GitHubIcon />}>Github</Button>
						</div>
					</section>
					<Divider className="custom-global">Or continue with</Divider>

					{isAccountExist ? <Login /> : <Register />}
					<Link underline="hover" onClick={() => setIsAccountExist(!isAccountExist)}>
						{isAccountExist ? "Haven't account yet? Register!" : "Or Log In with existing email."}
					</Link>
				</Paper>
			</div>
		</section>
	);
}
