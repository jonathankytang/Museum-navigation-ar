/*
ImageInformationViewController.swift
iosmuseumetest
 
Created by Jonathan Tang on 12/11/2018.
 
Completed with Medium tutorial
https://medium.com/codeandco/building-an-iphone-ar-museum-app-in-ios-11-with-apples-arkit-image-recognition-b07febd90a91
*/

import UIKit

// object that encapsulates all information for each picture

class ImageInformationViewController : UIViewController {
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var descriptionText: UITextView!
    
    var imageInformation : ImageInformation?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let actualImageInformation = imageInformation {
            self.nameLabel.text = actualImageInformation.name
            self.imageView.image = actualImageInformation.image
            self.descriptionText.text = actualImageInformation.description
        }
    }
    
    @IBAction func dismissView(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
}
