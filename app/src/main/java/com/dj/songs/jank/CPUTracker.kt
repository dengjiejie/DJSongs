package com.dj.songs.jank

import com.dj.songs.ShellUtils
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

/**
 *  author : dengjiejie
 *  date : 2022/7/26 3:26 下午
 *  description :
 */
class CPUTracker {


    fun getProcessCpuRateAdb(): String {
        val tv = StringBuilder()
        var rate = 0
        var result: String = ""

        try {
            var os: DataOutputStream? = null

            val p: Process = Runtime.getRuntime().exec( "su")
            os = DataOutputStream(p.outputStream)
            os.write("ls".toByteArray())
            os.writeBytes(ShellUtils.COMMAND_LINE_END)
            os.flush()
            os.writeBytes(ShellUtils.COMMAND_EXIT)
            os.flush()
            p.waitFor()


            val br = BufferedReader(InputStreamReader(p.inputStream))

            while (br.readLine().also { result += it } != null) {
                if (result.trim { it <= ' ' }.isEmpty()) {
                    continue
                } else {


//                    val CPUusr = Result.split("%").toTypedArray()
//                    tv.append(
//                        """
//                        USER:${CPUusr[0]}
//
//                        """.trimIndent()
//                    )
//                    val CPUusage = CPUusr[0].split("User").toTypedArray()
//                    val SYSusage = CPUusr[1].split("System").toTypedArray()
//                    tv.append("""CPU:${CPUusage[1].trim { it <= ' ' }} length:${CPUusage[1].trim { it <= ' ' }.length}
//"""
//                    )
//                    tv.append("""SYS:${SYSusage[1].trim { it <= ' ' }} length:${SYSusage[1].trim { it <= ' ' }.length}
//"""
//                    )
//                    rate = CPUusage[1].trim { it <= ' ' }.toInt() + SYSusage[1].trim { it <= ' ' }
//                        .toInt()
                    break
                }
            }
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        println(rate.toString() + "")
        return result
    }


    fun getProcessCpuRate(): Float {
        val totalCpuTime1 = getTotalCpuTime().toFloat()
        val processCpuTime1 = getAppCpuTime().toFloat()
        try {
            Thread.sleep(360)
        } catch (e: Exception) {
        }
        val totalCpuTime2 = getTotalCpuTime().toFloat()
        val processCpuTime2 = getAppCpuTime().toFloat()
        return (100 * (processCpuTime2 - processCpuTime1)
                / (totalCpuTime2 - totalCpuTime1))
    }

    fun getTotalCpuTime(): Long {   // 获取系统总CPU使用时间
        var cpuInfos: Array<String>? = null
        try {
            val reader = BufferedReader(
                InputStreamReader(
                    FileInputStream("/proc/stat")
                ), 1000
            )
            val load: String = reader.readLine()
            reader.close()
            cpuInfos = load.split(" ").toTypedArray()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return cpuInfos!![2].toLong() + cpuInfos[3].toLong() + cpuInfos[4]
            .toLong() + cpuInfos[6].toLong() + cpuInfos[5].toLong() + cpuInfos[7]
            .toLong() + cpuInfos[8].toLong()
    }

    fun getAppCpuTime(): Long {   // 获取应用占用的CPU时间
        var cpuInfos: Array<String>? = null
        try {
            val pid = android.os.Process.myPid()
            val reader = BufferedReader(
                InputStreamReader(
                    FileInputStream("/proc/$pid/stat")
                ), 1000
            )
            val load: String = reader.readLine()
            reader.close()
            cpuInfos = load.split(" ").toTypedArray()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return cpuInfos!![13].toLong() + cpuInfos[14].toLong() + cpuInfos[15]
            .toLong() + cpuInfos[16].toLong()
    }


}