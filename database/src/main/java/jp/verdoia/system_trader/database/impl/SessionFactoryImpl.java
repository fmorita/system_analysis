/*
 * Copyright (C) 2013 VERDOÏA Laurent <verdoialaurent@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jp.verdoia.system_trader.database.impl;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jp.verdoia.system_trader.database.api.Session;
import jp.verdoia.system_trader.database.api.SessionFactory;

/**
 * @author VERDOÏA Laurent <verdoialaurent@gmail.com>
 */
public class SessionFactoryImpl implements SessionFactory {
	private Properties m_properties;
	private String m_url;
	private SessionImpl m_session;

	public SessionFactoryImpl() {
		m_properties = new Properties();
		m_url = null;
		m_session = null;
	}

	@Override
	public void setUrl(final String url) throws NullPointerException {
		m_url = url;
	}

	@Override
	public void setProperties(final Properties properties)
			throws IllegalArgumentException {
		m_properties = (Properties) properties.clone();
	}

	@Override
	public Session createSession() throws IllegalStateException, SQLException {
		if (!isClosed()) {
			throw new IllegalStateException(
					"Not released session allready exists.");
		}

		if (m_session != null) {
			m_session = null;
		}

		final SessionImpl session = new SessionImpl();
		session.setConnection(DriverManager.getConnection(m_url, m_properties));
		m_session = session;
		return session;
	}

	@Override
	public void releaseSession(final Session session) throws SQLException,
			IllegalArgumentException {
		if (m_session != session) {
			throw new IllegalArgumentException("Unkonw session type: "
					+ session.getClass().toString());
		}

		close();
	}

	@Override
	public void close() throws SQLException {
		if (m_session == null) {
			return;
		}

		m_session.close();
		m_session = null;
	}

	@Override
	public boolean isClosed() {
		return m_session == null || m_session.isClosed();
	}

}
