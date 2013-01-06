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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jp.verdoia.system_trader.database.api.Session;

/**
 * @author VERDOÏA Laurent <verdoialaurent@gmail.com>
 */
public class SessionImpl implements Session {
	private Connection m_connection;

	public SessionImpl() {
		m_connection = null;
	}

	public void setConnection(final Connection connection)
			throws IllegalStateException {
		m_connection = connection;
	}

	@Override
	public boolean isClosed() {
		return m_connection == null;
	}

	@Override
	public void close() throws SQLException {
		if (m_connection == null) {
			return;
		}

		m_connection.close();
		m_connection = null;
	}

	@Override
	public Connection getConnection() {
		return m_connection;
	}

	@Override
	public void resetDatabase() throws SQLException {
		try (final Statement stmt = m_connection.createStatement()) {
			stmt.execute("DROP ALL OBJECTS");
			stmt.execute("RUNSCRIPT FROM 'classpath:/jp/verdoia/system_trader/database/sql/cleanInstall.sql'");
		}
	}

}
