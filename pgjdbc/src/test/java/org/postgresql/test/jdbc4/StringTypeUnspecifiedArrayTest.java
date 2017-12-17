/*
 * Copyright (c) 2007, PostgreSQL Global Development Group
 * See the LICENSE file in the project root for more information.
 */

package org.postgresql.test.jdbc4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.postgresql.PGProperty;
import org.postgresql.geometric.PGbox;
import org.postgresql.test.jdbc2.BaseTest4;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

@RunWith(Parameterized.class)
public class StringTypeUnspecifiedArrayTest extends BaseTest4 {

  private Connection _conn;

  public StringTypeUnspecifiedArrayTest(BinaryMode binaryMode) {
    setBinaryMode(binaryMode);
  }

  @Parameterized.Parameters(name = "binary = {0}")
  public static Iterable<Object[]> data() {
    Collection<Object[]> ids = new ArrayList<Object[]>();
    for (BinaryMode binaryMode : BinaryMode.values()) {
      ids.add(new Object[]{binaryMode});
    }
    return ids;
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    _conn = con;
  }

  @Override
  public void tearDown() throws SQLException {
    super.tearDown();
  }

  @Override
  protected void updateProperties(Properties props) {
    PGProperty.STRING_TYPE.set(props, "unspecified");
    super.updateProperties(props);
  }

  @Test
  public void testCreateArrayWithNonCachedType() throws Exception {
    PGbox[] in = new PGbox[0];
    Array a = _conn.createArrayOf("box", in);
    Assert.assertEquals(1111, a.getBaseType());
  }
}