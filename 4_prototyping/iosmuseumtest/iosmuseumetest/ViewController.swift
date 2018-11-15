/*
ViewController.swift
iosmuseumetest

Created by Jonathan Tang on 12/11/2018.

Completed with Medium tutorial
https://medium.com/codeandco/building-an-iphone-ar-museum-app-in-ios-11-with-apples-arkit-image-recognition-b07febd90a91
*/

import UIKit
import SpriteKit
import ARKit

struct ImageInformation {
    let name: String
    let description: String
    let image: UIImage
}
//
//class GetGPS: {
//    let locationManager = CLLocationManager()
//    
//    
//}

class ViewController: UIViewController, ARSKViewDelegate {
    @IBOutlet var sceneView: ARSKView!
    var selectedImage : ImageInformation?
    
    // storing image information
    let images = ["monalisa" : ImageInformation(name: "Mona Lisa", description: "The Mona Lisa is a half-length portrait painting by the Italian Renaissance artist Leonardo da Vinci that has been described as 'the best known, the most visited, the most written about, the most sung about, the most parodied work of art in the world'.", image: UIImage(named: "monalisa")!)]
    
    // loading view
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // receiving messages about the rendering process
        sceneView.delegate = self
        // show fps
        sceneView.showsFPS = true
        // show node count
        sceneView.showsNodeCount = true
        
        if let scene = SKScene(fileNamed: "Scene") {
            sceneView.presentScene(scene)
        }
        
        guard let referenceImages = ARReferenceImage.referenceImages(inGroupNamed: "Mona Lisa Room", bundle: nil) else {
            fatalError("Missing expected asset catalog resources.")
        }
        
        let configuration = ARWorldTrackingConfiguration()
        
        // Detection of images with AR
        configuration.detectionImages = referenceImages
        
        sceneView.session.run(configuration, options: [.resetTracking, .removeExistingAnchors])
    }
    
    
    // MARK: - ARSKViewDelegate
    
    // Delegate method automatically triggers every time a new node is added to a new view
    func view(_ view: ARSKView, nodeFor anchor: ARAnchor) -> SKNode? {
        
        // Checks if it anchors the delgate method calling for ARImageAnchor

        if let imageAnchor = anchor as? ARImageAnchor,
            // gets refrence image name
            let referenceImageName = imageAnchor.referenceImage.name,
            
            // checks if image name is in the images collection
            let scannedImage =  self.images[referenceImageName] {
            // sets the selected image in the controller
            self.selectedImage = scannedImage
            // Call modal segue
            self.performSegue(withIdentifier: "showImageInformation", sender: self)
            // Call private function to create and return an already scanned image
            return imageSeenMarker()
        }
        
        return nil
    }

    // function to add to envionment when image has been already scanned
    private func imageSeenMarker() -> SKLabelNode {
        // add check emoji onto the view
        let labelNode = SKLabelNode(text: "✅")
        labelNode.horizontalAlignmentMode = .center
        labelNode.verticalAlignmentMode = .center
        
        return labelNode
    }
    
    // add image information to ImageInformationViewController and Modal
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showImageInformation"{
            if let imageInformationVC = segue.destination as? ImageInformationViewController,
                let actualSelectedImage = selectedImage {
                imageInformationVC.imageInformation = actualSelectedImage
            }
        }
    }
}
