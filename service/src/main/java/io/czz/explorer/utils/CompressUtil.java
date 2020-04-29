package io.czz.explorer.utils;

import org.jooby.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/**
 * @author xingkong
 * @date 2019/12/2 下午1:59
 */
public class CompressUtil {


    public static byte[] compressToGzip(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    private static final String ENCODING = "UTF-8";
    /**
     * 压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] compress(byte[] data) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 压缩
        GZIPOutputStream gos = new GZIPOutputStream(baos);

        gos.write(data, 0, data.length);

        gos.finish();

        byte[] output = baos.toByteArray();

        baos.flush();
        baos.close();

        return output;
    }

    /**
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public static void excute(Response response, String str)
            throws ServletException, IOException {

        byte[] data = str.toString().getBytes(ENCODING);

        try {
            byte[] output = compress(data);
            // 设置Content-Encoding，这是关键点！
            response.header("Content-Encoding", "gzip");
            response.type("text/plain;charset=utf-8");
            // 设置字符集
            response.charset(Charset.defaultCharset());
            // 设定输出流中内容长度
            response.length(output.length);

            OutputStream out = new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                }
            };
            out.write(output);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
