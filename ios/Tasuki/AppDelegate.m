/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

#import "AppDelegate.h"

#import <React/RCTBridge.h>
#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>
#import <ReactNativeNavigation/ReactNativeNavigation.h>
#import <Fabric/Fabric.h>
#import <Crashlytics/Crashlytics.h>
#import <Firebase.h>
#import "RNSplashScreen.h"

@implementation AppDelegate
  
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
  {
    [Fabric with:@[[Crashlytics class]]];
    
    // firebase
    NSString *filePath;
#ifdef PRODUCTION
    filePath = [[NSBundle mainBundle] pathForResource:@"GoogleService-Info" ofType:@"plist"];
#elif STAGING
    filePath = [[NSBundle mainBundle] pathForResource:@"GoogleService-Info-Stg" ofType:@"plist"];
#else
    filePath = [[NSBundle mainBundle] pathForResource:@"GoogleService-Info-Dev" ofType:@"plist"];
#endif
    
    FIROptions *options = [[FIROptions alloc] initWithContentsOfFile:filePath];
    [FIRApp configureWithOptions:options];
    
    NSURL *jsCodeLocation = [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index" fallbackResource:nil];
    [ReactNativeNavigation bootstrap:jsCodeLocation launchOptions:launchOptions];
    
    [RNSplashScreen show];
    return YES;
  }
  
  @end
