package or.wr.bookrecommendationsystem.server;


import org.springframework.web.multipart.MultipartFile;


import java.io.*;

public class FileService {

    public String getArContentFileDir() {
        return arContentFileDir;
    }

/*    private String boCoverPhotoDir = "D:/1/2/apache-tomcat-9.0.22-test/webapps/recommendation/imgs/bookCoverImgs/";

    private String boCoverPhotoPre = "http://localhost:8081/recommendation/imgs/bookCoverImgs/";

    private String arContentFileDir = "E:/projects/ideaProjects/recommendation/articles/";

    private String arCoverPhotoDir = "D:/1/2/apache-tomcat-9.0.22-test/webapps/recommendation/imgs/articleCoverImgs/";

    private String arCoverPhotoPre = "http://localhost:8081/recommendation/imgs/articleCoverImgs/";*/


    private String boCoverPhotoDir = "/usr/local/nginx/images/recommendation/imgs/bookCoverImgs/";

    private String boCoverPhotoPre = "https://wrtcloud.cn/images/recommendation/imgs/bookCoverImgs/";

    private String arContentFileDir = "/root/projectData/recommendation/articles/";

    private String arCoverPhotoDir = "/usr/local/nginx/images/recommendation/imgs/articleCoverImgs/";

    private String arCoverPhotoPre = "https://wrtcloud.cn/images/recommendation/imgs/articleCoverImgs/";

    /*
     * param content 正文完整字节
     * param fileName 将保存的文件名，不需要文件扩展名
     */
    public void saveContentAsHtmlFile(byte[] content, String fileName) throws IOException {
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(arContentFileDir + fileName + ".html"));
        outputStream.write(content);
        outputStream.flush();
        outputStream.close();
//        return arContentFileDir+fileName+".html";
    }


    /*
     * param String dir 保存的目录
     * param String fileName 将保存的文件名，需要文件带扩展名
     * return String[2] 返回图片服务器下的访问路径，以及存储路径
     */
    private String[] saveFile(String dir, String url, MultipartFile file, String fileName){
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dir+fileName))) {
            outputStream.write(file.getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }
        return new String[]{dir+fileName,url+fileName};
    }

    public String[] saveArticleCoverPhoto(MultipartFile file, String fileName){
        return saveFile(arCoverPhotoDir,arCoverPhotoPre,file,fileName);
    }

    public String[] saveBookCoverPhoto(MultipartFile file, String fileName){

        return saveFile(boCoverPhotoDir,boCoverPhotoPre,file,fileName);

    }
    //根据路径删除文件
    public void deleteFileByPath(String path){
        new File(path).delete();
    }


    public boolean reWriteFile(byte[] bytes, String absolutePath){
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(absolutePath))) {
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("重写文件失败");
        }
        return true;
    }


}
