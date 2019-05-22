//******************************************************************************
//
//
//Licensed Materials - Property of IBM
//
//5724-J39
//
//(C) Copyright IBM Corp. 2006, 2007 All Rights Reserved
//
//US Government Users Restricted Rights - Use, duplication or
//disclosure restricted by GSA ADP Schedule Contract with
//IBM Corp.
//
//
//******************************************************************************

package com.education.classroom.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logging utilities. This class must be loaded first to init the logger.
 * 
 */
public class LogUtil {

    private static final Logger logger = Logger.getLogger("com.creditharmony.common");
    private static final Logger log4jLogger = Logger.getLogger("com.ibm.common");

    private static boolean isVerbose = false;

    private static boolean isTraceMode = false;

    /**
     * sets the logger to trace mode. overwrites any previous setting.
     */
    public static void setTrace() {
        if (LogUtil.isTraceMode) {
            return;
        }
        LogUtil.logger.setLevel(Level.ALL);
        LogUtil.isTraceMode = true;
    }

    /**
     * sets the logger to verbose mode. overwrites any previous setting.
     */
    public static void setVerbose() {
        // trace mode overrides this
        if (LogUtil.isTraceMode || LogUtil.isVerbose) {
            return;
        }
        LogUtil.logger.setLevel(Level.ALL);
        LogUtil.isVerbose = true;
    }

    /**
     * @param name
     * @return the appropriate logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * @param name
     * @return the appropriate logger
     */
    public static Logger getLog4JLogger() {
        return log4jLogger;
    }

    /**
     * Tells if logging is verbose
     * 
     * @return true if it is. false if not
     */
    public static boolean isVerbose() {
        return (LogUtil.isTraceMode || LogUtil.isVerbose);
    }

    /**
     * Tells if application in trace mode
     * 
     * @return true if it is. false if not
     */
    public static boolean isTraceMode() {
        return LogUtil.isTraceMode;
    }
}
