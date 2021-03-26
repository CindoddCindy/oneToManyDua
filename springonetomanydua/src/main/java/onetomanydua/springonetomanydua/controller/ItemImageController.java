package onetomanydua.springonetomanydua.controller;

import onetomanydua.springonetomanydua.exception.ResourceNotFoundExcption;
import onetomanydua.springonetomanydua.model.ItemImage;
import onetomanydua.springonetomanydua.repository.PostRepository;
import onetomanydua.springonetomanydua.service.ItemImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/futrash/item/image")
public class ItemImageController {

    @Autowired
    private ItemImageService itemImageService;

    @Autowired
    PostRepository postRepository;

    /*
    @PostMapping("/upload")

    public ResponseEntity<ResponseMessage> uploadImageItem(@RequestParam("file") MultipartFile file) {
        String message = "";
        ItemImage itemImage = new ItemImage();
        try {
            itemImageService.store(file);

            message = itemImageService.store(file).getId();
            ;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

     */


    @PostMapping("/items/{itemId}/itemImage")
    public ItemImage createImage(@RequestParam("file") MultipartFile file, @PathVariable (value = "postId") Long postId
                                 ) {
        ItemImage itemImage= new ItemImage();
        return postRepository.findById(postId).map(post -> {
            itemImage.setPost(post);
            try {
                return itemImageService.store(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).orElseThrow(() -> new ResourceNotFoundExcption("ItemId " + postId + " not found"));
    }


/*

    @GetMapping("/files/")
    public ResponseEntity<List<ResponseItem>> getListImageItem() {
        List<ResponseItem> files = itemImageService.getAllFiles().map(itemImage -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(itemImage.getId())
                    .toUriString();

            return new ResponseItem(
                    itemImage.getName(),
                    fileDownloadUri,
                    itemImage.getType(),
                    itemImage.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


 */
    /*





    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFileImageItem(@PathVariable String id) {
        ItemImage fileDB = itemImageService.getFile(id);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(fileDB.getId())
                .toUriString();



        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"" + fileDownloadUri)
                .body(fileDB.getData());






    }

     */


}
