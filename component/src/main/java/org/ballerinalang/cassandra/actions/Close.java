/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.cassandra.actions;

import org.ballerinalang.bre.Context;
import org.ballerinalang.cassandra.CassandraDataSource;
import org.ballerinalang.cassandra.CassandraDataSourceUtils;
import org.ballerinalang.cassandra.Constants;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BStruct;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;

/**
 * {@code Close} action is used to close the Cassandra session.
 *
 * @since 0.95.0
 */
@BallerinaFunction(
        orgName = "ballerina", packageName = "cassandra",
        functionName = "close",
        args = {
                @Argument(name = "parameters", type = TypeKind.STRUCT, structType = Constants.CALLER_ACTIONS,
                          structPackage = "ballerina.cassandra")}
)
public class Close extends AbstractCassandraAction {

    @Override
    public void execute(Context context) {
        BStruct bConnector = (BStruct) context.getRefArgument(0);
        CassandraDataSource datasource = (CassandraDataSource) bConnector.getNativeData(Constants.CALLER_ACTIONS);
        try {
            close(datasource);
        } catch (Throwable e) {
            context.setReturnValues(CassandraDataSourceUtils.getCassandraConnectorError(context, e));
        }
    }
}
