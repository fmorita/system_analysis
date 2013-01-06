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
package jp.verdoia.system_trader.cui;

import java.util.Properties;

import jp.verdoia.system_trader.database.api.Session;
import jp.verdoia.system_trader.database.api.SessionFactory;
import jp.verdoia.system_trader.database.api.SessionFactoryBuilder;

/**
 * @author VERDOÏA Laurent <verdoialaurent@gmail.com>
 */
public class Main {

	public static void main(final String[] args) throws Exception {
		final Properties props = new Properties();
		props.setProperty("user", "sa");
		props.setProperty("password", "");
		final String url = "jdbc:h2:~/test";

		try (final SessionFactory factory = SessionFactoryBuilder.build()) {
			factory.setUrl(url);
			factory.setProperties(props);
			final Session session = factory.createSession();
			session.resetDatabase();
		}
	}

}
