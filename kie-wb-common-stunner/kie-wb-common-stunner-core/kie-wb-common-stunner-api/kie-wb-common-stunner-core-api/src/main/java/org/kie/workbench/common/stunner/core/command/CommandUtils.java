/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.core.command;

import org.kie.workbench.common.stunner.core.command.batch.BatchCommandResult;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CommandUtils {

    public static boolean isError( final CommandResult<?> result ) {
        if ( result instanceof BatchCommandResult ) {
            final BatchCommandResult<?> batchCommandResult = ( BatchCommandResult<?> ) result;
            return isBatchCommandResultError( batchCommandResult );

        }
        return isCommandResultError( result );
    }

    private static boolean isCommandResultError( final CommandResult<?> result ) {
        return result != null && CommandResult.Type.ERROR.equals( result.getType() );
    }

    @SuppressWarnings( "unchecked" )
    private static boolean isBatchCommandResultError( final BatchCommandResult result ) {
        return result != null && CommandResult.Type.ERROR.equals( result.getType() );
    }

    public static <V> List<V> toList( final Iterable<V> iterable ) {
        final List<V> result = new LinkedList<>();
        final Iterator<V> iterator = iterable.iterator();
        while ( iterator.hasNext() ) {
            final V v = iterator.next();
            result.add( v );
        }
        return result;
    }
}