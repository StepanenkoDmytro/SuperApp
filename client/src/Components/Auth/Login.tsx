import React from 'react';
import { Button, Checkbox, FormControlLabel, Link, TextField } from '@mui/material';
import styles from '../../styles/Components/Auth/login.module.scss';

export function Login() {
	return (
		<form className={ styles.loginForm }>
			<TextField
				id="email"
				label="Email address"
				variant="outlined"
				type="email"
				error={false}
				helperText={false}
				margin={'normal'}
			/>

			<TextField
				id="password"
				label="Password"
				variant="outlined"
				type="password"
				error={false}
				helperText={false}
				margin={'normal'}
			/>

			<div className={ styles.rememberGroup}>
				<FormControlLabel control={<Checkbox id="remember" />} label="Remember me" />
				<Link underline="hover" onClick={() => null}>
					{'Forgot password?'}
				</Link>
			</div>

			<Button type="submit" variant="contained" disabled={true}>Sign in</Button>
		</form>
	);
}
