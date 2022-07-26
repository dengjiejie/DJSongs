package com.dj.songs

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.AssertionError
import java.lang.Exception
import java.lang.StringBuilder

/**
 *  author : dengjiejie
 *  date : 2022/7/26 6:35 下午
 *  description :
 */

class ShellUtils private constructor() {
    /**
     * 运行结果
     *
     *  * [CommandResult.result] means result of command, 0 means normal,
     * else means error, same to excute in linux shell
     *  * [CommandResult.successMsg] means success message of command
     * result
     *  * [CommandResult.errorMsg] means error message of command result
     *
     *
     * @author [Trinea](http://www.trinea.cn)
     * 2013-5-16
     */
    class CommandResult {
        /** 运行结果  */
        var result: Int

        /** 运行成功结果  */
        var successMsg: String? = null

        /** 运行失败结果  */
        var errorMsg: String? = null

        constructor(result: Int) {
            this.result = result
        }

        constructor(result: Int, successMsg: String?, errorMsg: String?) {
            this.result = result
            this.successMsg = successMsg
            this.errorMsg = errorMsg
        }

        override fun toString(): String {
            return "result = $result, successMsg = $successMsg, errorMsg = $errorMsg."
        }


    }

    companion object {
        private const val TAG = "ShellUtils"
        const val COMMAND_SU = "su"
        const val COMMAND_SH = "sh"
        const val COMMAND_EXIT = "exit\n"
        const val COMMAND_LINE_END = "\n"

        /**
         * 查看是否有了root权限
         *
         * @return
         */
        fun checkRootPermission(): Boolean {
            return execCommand("echo root", true, false).result == 0
        }

        /**
         * 执行shell命令，默认返回结果
         *
         * @param command
         * command
         * @param isRoot
         * need root permission
         * @return
         * @see ShellUtils.execCommand
         */
        fun execCommand(command: String, isRoot: Boolean): CommandResult {
            return execCommand(arrayOf(command), isRoot, true)
        }

        /**
         * 执行shell命令，默认返回结果
         *
         * @param commands
         * command list
         * @param isRoot
         * need root permission
         * @return
         * @see ShellUtils.execCommand
         */
        fun execCommand(
            commands: List<String>?,
            isRoot: Boolean
        ): CommandResult {
            return execCommand(
                commands?.toTypedArray(),
                isRoot, true
            )
        }

        /**
         * execute shell command
         *
         * @param command
         * command
         * @param isRoot
         * need root permission
         * @param isNeedResultMsg
         * whether need result msg
         * @return
         * @see ShellUtils.execCommand
         */
        fun execCommand(
            command: String, isRoot: Boolean,
            isNeedResultMsg: Boolean
        ): CommandResult {
            return execCommand(arrayOf(command), isRoot, isNeedResultMsg)
        }

        /**
         * execute shell commands
         *
         * @param commands
         * command list
         * @param isRoot
         * need root permission
         * @param isNeedResultMsg
         * result
         * @return
         * @see ShellUtils.execCommand
         */
        fun execCommand(
            commands: List<String>?,
            isRoot: Boolean, isNeedResultMsg: Boolean
        ): CommandResult {
            return execCommand(
                commands?.toTypedArray(),
                isRoot, isNeedResultMsg
            )
        }
        /**
         * execute shell commands
         *
         * @param commands
         * command array
         * @param isRoot
         * need root permission
         * @param isNeedResultMsg
         * result
         * @return
         *  * if isNeedResultMsg is false, [CommandResult.successMsg]
         * is null and [CommandResult.errorMsg] is null.
         *  * if [CommandResult.result] is -1, there maybe some
         * excepiton.
         *
         */
        /**
         * 执行shell命令，默认返回结果
         *
         * @param commands
         * command array
         * @param isRoot
         * need root permission
         * @return
         * @see ShellUtils.execCommand
         */
        @JvmOverloads
        fun execCommand(
            commands: Array<String>?, isRoot: Boolean,
            isNeedResultMsg: Boolean = true
        ): CommandResult {
            var result = -1
            if (commands == null || commands.size == 0) {
                return CommandResult(result, null, null)
            }
            var process: Process? = null
            var successResult: BufferedReader? = null
            var errorResult: BufferedReader? = null
            var successMsg: StringBuilder? = null
            var errorMsg: StringBuilder? = null
            var os: DataOutputStream? = null
            try {
                process = Runtime.getRuntime().exec(if (isRoot) COMMAND_SU else COMMAND_SH)
                os = DataOutputStream(process.outputStream)
                for (command in commands) {
                    if (command == null) {
                        continue
                    }
                    // donnot use os.writeBytes(commmand), avoid chinese charset
                    // error
                    os.write(command.toByteArray())
                    os.writeBytes(COMMAND_LINE_END)
                    os.flush()
                }
                os.writeBytes(COMMAND_EXIT)
                os.flush()
                result = process.waitFor()
                // get command result
                if (isNeedResultMsg) {
                    successMsg = StringBuilder()
                    errorMsg = StringBuilder()
                    successResult = BufferedReader(
                        InputStreamReader(
                            process.inputStream
                        )
                    )
                    errorResult = BufferedReader(
                        InputStreamReader(
                            process.errorStream
                        )
                    )
                    var s: String?
                    while (successResult.readLine().also { s = it } != null) {
                        successMsg.append(s)
                    }
                    while (errorResult.readLine().also { s = it } != null) {
                        errorMsg.append(s)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    os?.close()
                    successResult?.close()
                    errorResult?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                process?.destroy()
            }
            return CommandResult(result, successMsg?.toString(), errorMsg?.toString())
        }
    }

    init {
        throw AssertionError()
    }
}

