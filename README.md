# WAT Compiler

(kinda) *compiles* abstract Source-code into WebAssembly Text format. In other words, SWAT 🚨 into WAT 👵.

> Attention: it uses print functions to append wat code in terminal. Ofc it is a bad practice, but the goal was in at least working "compiler", so for the simplicity I decided to just use print to stdout as output

## It supports

* Local variable definition: `let a = 2;`
* Definition with expression and variables: `let b = a + 2;`
* Expressions and equations
* If and while statements

## What is ~~wrongly~~ implemented

* BNF-grammar for ANTLR supporting definitions, expressions, equations, *whitespaces (wow)*, conditions and loops
* Cranky generator to generate something simular to WAT right in the console (I definitely need to know more theory on intermediate representation and s-expressions)
* Local variable assignment
* Math expression evaluation
* Local variables read-write process
* If and if-else conditions with equations
* While loops with equations

## Example

```rust
let a = 1 + 2;
let b = 2 + a;

if (a == b) {
    let c = 3;
} else {
    let d = 5 + b;
}

while (a != b) {
    let f = (a + d + b) / 3;
}
```

```
(module
    (memory 1)
    (start 0)
    (func
        (local $a i32) (local $b i32) (local $c i32) (local $d i32) (local $f i32)

        local.const 1
        local.const 2
        i32.add
        local.set $a
         
        local.const 2
        local.get a
        i32.add
        local.set $b 
        
        local.get a
        local.get b
        i32.eq
        
        if i32
            local.const 3
            local.set $c 
        else
            local.const 5
            local.get b
            i32.add
            local.set $d 
        end
        
        block $b1
            loop $l1
                local.get a
                local.get b
                i32.ne
        
                local.get a
                local.get d
                i32.add
                local.get b
                i32.add
                
                local.const 3
                i32.div_s
                local.set $f 
                
                br_if $b1
                br $l1
            end
        end
    )
)
```

## Resources

### WAT

* [Introduction to WebAssembly by Rasmus Andersson](https://rsms.me/wasm-intro)
* [WebAssembly Design Reference](https://github.com/WebAssembly/design)
* [Understanding WebAssembly text format](https://developer.mozilla.org/en-US/docs/WebAssembly/Understanding_the_text_format)
* [Wasm Explorer](https://mbebenita.github.io/WasmExplorer/)

### WebAssembly

* [Rust 🦀 and WebAssembly 🕸](https://rustwasm.github.io/book/)
* [Rust, WebAssembly, and the future of Serverless by Steve Klabnik](https://www.youtube.com/watch?v=CMB6AlE1QuI)
* [Bringing WebAssembly outside the web with WASI by Lin Clark](https://youtu.be/fh9WXPu0hw8)
* [Compiling from Rust to WebAssembly](https://developer.mozilla.org/en-US/docs/WebAssembly/rust_to_wasm)

### Compilers and analyzers

* [C to WebAssembly by Surma](https://surma.dev/things/c-to-webassembly/)
* [Build your own WebAssembly Compiler](https://blog.scottlogic.com/2019/05/17/webassembly-compiler.html)
* [Алексей Кладов - rust-analyzer (СПб, 2019.09.15)](https://youtu.be/74Y1n2v-gos)

---
```
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXOOXNWWWNNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNOl,;dO0000OxxkOKNWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW0c';xXWWWWWWWWXKOOOk0NWWWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXkoo0WWWWNNWWWWNXNWWWXOdkNWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWOcxNWWWNkl;;:oKXxxkxONWWKdxXWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWKcdNWWKd,.;dxxx0KOkc',lkXWNdlXWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWx;0WWXc.,kNWWWWWWWWNKOOxoxWK:oWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWk:OWWO:lKWWWWWWN0KWWWMWMOoKMxoXMWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWW0,lWNxkWWXkllllddkOdddONKkXMOdKWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWNo.lxxXWNXx:;;;dNNo,;lkN0lOWdoNWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWO,':OWWWWNNNKOXWW0OXXNWNdxx:OWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWX:,dKWWWWWKkolx00dlkNWWWx,;kWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWNc.xWWWWWWx,ldlddoc;dNWWk,xWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWl ,0WWWWWOcoxxkOOOooNWWxdNWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWd,;oNWWWWKkONWWWWWXkKMKoOWWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWWWWWWNK0kokklOWWWWX0kddooodONMXdxNWWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWWWWXOxxxdol:dKxdXWWWWWWWXK000XWWNo;d0XWWWWWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWWWWXxoodOKNWWWXkd:oKxOWWWWWWWWWWWWWOcxkdxkkOKWWWWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWWWW0old0NWWWWWWWWWN0xd:;dXWWWWWWWWWWWKONWWWN0kkkkKWWWWWWWWWWWWWWWWW
WWWWWWWWWWWWWNOolxXWWWWWWWWWWWWWWWNOdclxO0NWWWWWWWWWWWWWWWWXd:lkXWWWWWWWWWWWWWWW
WWWWWWWWWWWNkooONWWWWWWWWWWWWWWWWWWWWN0kxddxkO0KKXXKK0OOkxddxKNkdONWWWWWWWWWWWWW
WWWWWWWWWKdl:;xWWWWWWWWWWWWWWWWWWWWWWWWWWWWX0OkkkkkxdxkkO0XWWWWWXxoxXWWWWWWWWWWW
WWWWWWWXo:dKNx;dWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWKol0WWWWWWWWWWWXdlOWWWWWWWWWW
WWWWWNx:oKWWWWx;xWWWWWWW0xkXWXkxKWXkkKWXOkdodOXWWKx, .l0WWWWWWWWWWWWO:lKWWWWWWWW
WWWW0llOWWWWWWWd:OWWWWWN: .kMO. dWO. o0, .',..,ONd'  .,dNWWWWWWWWWWWWKlcKWWWWWWW
WWW0cxNWWWWWWWWNdxWWWWWN: .kMO..dM0' dXxlxK0l. :XWk. oNWWWWWWWWWWWWWWWNxoKWWWWWW
WW0:oNWWWWWWWWWWXKNWWWWWo  dWx. lNk..OWOdc,,'. ,KMk. dWWWWWWWWWWWWWWWWWKooKWWWWW
WXccXWWWWWWWWWWWWWWWWWWW0, ,x,  'd; lNk.  cKK; '0Mk. lWWWWWWWWWWWWWWWW0lkKxONWWW
Wx,kWWWWWWWWWWWWWWWWWWWWWk.  .c,  .:KWd.  ;xd' '0MO. ,KWWWWWWWWWWWWWW0xxkXXxkNWW
Wd:KWWWWWWWWWWWWWWWWWWWWWWk,,xW0:'oXWWXxc:''...:KMK:.:0WWWWWWWWWWWWWNOKW0KWXxkNW
NclNWWWWWWWWWWWWWWWWWWWWWWWXXWWWNXWWWWWWWWNXXXXNWWWXXWWWWWWWWWWWWWWWWWWWWWWWXxOW
0ckWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXkK
NXWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNN
```
