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
package jp.verdoia.system_trader.database.api;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author VERDOÏA Laurent <verdoialaurent@gmail.com>
 */
public interface SessionFactory extends AutoCloseable {
	void setUrl(String url) throws NullPointerException;

	void setProperties(Properties properties) throws IllegalArgumentException;

	Session createSession() throws IllegalStateException, SQLException;

	void releaseSession(Session session) throws SQLException,
			IllegalArgumentException;

	@Override
	void close() throws SQLException;

	boolean isClosed();
}
