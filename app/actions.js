'use server';

import { query } from '@/lib/db';

// ─── INQUIRIES ─────────────────────────────────────────────────────────────
export async function submitInquiry(formData) {
    const name = formData.get('name');
    const email = formData.get('email');
    const phone = formData.get('phone');
    const subject = formData.get('subject');
    const message = formData.get('message');

    try {
        await query(
            'INSERT INTO inquiries (name, email, phone, subject, message) VALUES (?, ?, ?, ?, ?)',
            [name, email, phone, subject, message]
        );
        return { success: true };
    } catch (error) {
        console.error('Database error:', error);
        return { success: false, error: error.message || 'Failed to submit inquiry' };
    }
}

// ─── USERS ─────────────────────────────────────────────────────────────────
export async function registerUser(formData) {
    const name = formData.get('name');
    const email = formData.get('email');
    const password = formData.get('password');

    try {
        const existingUsers = await query('SELECT id FROM users WHERE email = ?', [email]);
        if (existingUsers.length > 0) {
            return { success: false, error: 'Email already registered' };
        }

        await query(
            'INSERT INTO users (name, email, password) VALUES (?, ?, ?)',
            [name, email, password]
        );

        return { success: true };
    } catch (error) {
        console.error('Registration error:', error);
        return { success: false, error: error.message || 'Registration failed' };
    }
}

export async function loginUser(email, password) {
    try {
        const users = await query(
            'SELECT id, name, email FROM users WHERE email = ? AND password = ?',
            [email, password]
        );

        if (users.length > 0) {
            return { success: true, user: users[0] };
        } else {
            return { success: false, error: 'Invalid email or password' };
        }
    } catch (error) {
        console.error('Login error:', error);
        return { success: false, error: 'Login failed' };
    }
}
