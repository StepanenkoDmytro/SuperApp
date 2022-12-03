import React from 'react';
import { Button, Checkbox, FormControlLabel, Link, TextField } from '@mui/material';

export function Login() {
	return (
		<form style={{ display: 'flex', flexDirection: 'column' }}>
			<TextField id="email" label="Email address" variant="outlined" type="email"/>

			<TextField id="password" label="Password" variant="outlined" type="password"/>

			<div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center'}}>
				<FormControlLabel control={<Checkbox id="remember" />} label="Remember me" />
				<Link underline="hover" onClick={() => null}>
					{'Forgot password?'}
				</Link>
			</div>

			<Button type="submit" variant="contained">Sign in</Button>
		</form>
	);
}
