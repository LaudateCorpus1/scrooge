//
// Autogenerated by Scrooge
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//
import Foundation
import TwitterApacheThrift
public struct UnionClassA: Hashable {
    public var someString: String
    enum CodingKeys: Int, CodingKey {
        case someString = 1
    }
    public init(someString: String) {
        self.someString = someString
    }
}
extension UnionClassA: ThriftCodable {}
