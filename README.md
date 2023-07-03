# grpc-quarkus-blocking-bug

Repo to reproduce a bug when combining the authentication mechanism of quarkus and gRPC.
The bug is described on this GitHub issue: [https://github.com/quarkusio/quarkus/issues/34439](https://github.com/quarkusio/quarkus/issues/34439)

### How to reproduce

Run the app and do a curl and check the log of the app.

Example of results:
- curl -v localhost:8085/helloB => Blocking call without roles allowed, is on eventloop: false
- curl -v localhost:8085/helloBWithR => Blocking call with roles allowed, is on eventloop: true (**Should be false**)
- curl -v localhost:8085/helloNonB => Non blocking call  with roles allowed, is on eventloop: true
