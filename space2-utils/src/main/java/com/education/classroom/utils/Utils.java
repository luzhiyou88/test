package com.education.classroom.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;

import com.education.classroom.utils.exception.ECMSystemException;

public class Utils{
	public static LinkedList<String> argsToLinkedList(final String[] args) {
        // we'll convert to linked list so that consecutive iterations over the
        // args don't have to parse the whole list and only the list which was
        // not parsed by earlier operations. we chose linked list as we'd have a
        // number of deletions from the list.
    	
        final LinkedList<String> list = new LinkedList<String>();
        
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                list.addLast(args[i].trim());
            }
        }
        return list;
    }
    /**
     * Removes the current elemet from the iterator and get the next element and
     * then removes that element too.
     * 
     * @param iter
     *            the iterator
     * @return next elemet. null if no more elements present
     */
    public static String removeAndGetNext(final Iterator<String> iter) {
        String string = null;
        try {
            iter.remove();
        } catch (final IllegalStateException ex) {
            // ignore, we could not remove the element, it was already removed
        }
        string = iter.next();
        iter.remove();
        return string;
    }
    /**
     * Gets the normalized host name. If the host name is an IPv6 address, it is
     * surrounded by []
     * 
     * @param host
     *            unnormalized host name/IP.
     * @return normalized host name/IP.
     */
    public static String getNormalizedHost(String host) {
        StringBuilder hostBldr = new StringBuilder();
        if (host.indexOf(':') != -1 && host.charAt(0) != '[') {
            // IPv6 address
            hostBldr.append('[').append(host).append(']');
        } else {
            hostBldr.append(host);
        }
        return hostBldr.toString();
    }

    public static String[] combineStringArray(String[] src1, String[] src2) {
        String[] result = new String[src1.length + src2.length];
        System.arraycopy(src1, 0, result, 0, src1.length);
        System.arraycopy(src2, 0, result, src1.length, src2.length);
        return result;
    }

    public static void handleRuntimeExcetion(String[] args, Exception ex, String className, String methodName) {
        StringBuffer errMsg = new StringBuffer("Runtime Error in " + className + " method: " + methodName + " \r\n");

        if (args != null) {
            for (int i = 0; i < args.length; i++)
                errMsg.append("Parameters:" + args[i] + "\r\n");
        }
        LogUtil.getLogger().log(Level.SEVERE, errMsg.toString(), ex);
        LogUtil.getLog4JLogger().info(errMsg.toString());
    }

    public static void logWarning(String warningMsg) {
    	LogUtil.getLogger().log(Level.WARNING, warningMsg, (Exception)null);
    	LogUtil.getLog4JLogger().warning(warningMsg);
    }

    public static void logDebug(String debugMsg) {
    	LogUtil.getLogger().log(Level.INFO, debugMsg, (Exception)null);
    	LogUtil.getLog4JLogger().info(debugMsg);
    }

    public static void handleAndThrowRuntimeExcetion(String[] args, ECMSystemException ex, String className, String methodName) throws ECMSystemException {

        //handleRuntimeExcetion(args, ex, className, methodName);
        throw ex;
    }


}


